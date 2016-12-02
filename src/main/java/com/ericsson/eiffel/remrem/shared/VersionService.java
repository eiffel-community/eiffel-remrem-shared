package com.ericsson.eiffel.remrem.shared;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 * This class will search in all registered jars and their manifest file for
 * attribute Remrem-Version-Key. It will return a list with all versions found.
 * 
 * @author evasiba
 *
 */
public class VersionService {

    private static final String REMREM_VERSION_KEY = "remremVersionKey";
    private static final String IS_ENDPOINT_VERSION = "isEndpointVersion";
    private static final String ENDPOINT_VERSION = "endpointVersions";
    private static final String SERVICE_VERSION = "serviceVersion";

    /**
     * This method will load and parse the MINIFEST files to get the version of
     * the loaded messaging protocols. It is required to define the versions as
     * mainifest attributes in the build.gradle or pom.xml files using
     * attributes "remremVersionKey" and "isEndpointVersion" to specify the type
     * of the protocol or service and if it is endpoint or not respectively.
     * Example for build.gradle: manifest { attributes('remremVersionKey':
     * 'semanticsVersion') attributes('semanticsVersion': version)
     * attributes('isEndpointVersion': 'true') }
     * 
     * @return a map containing the protocol and service types with their
     *         versions {"endpointVersions" : {"semanticsVersion" : "1.1.1"},
     *         "serviceVersion": {"remremGenerateVersion": "0.1.1"}}
     */
    public static Map<String, Map<String, String>> getMessagingVersions() {
        Enumeration<?> resEnum;
        Map<String, Map<String, String>> versions = new HashMap<>();
        Map<String, String> endpointVersions = new HashMap<String, String>();
        Map<String, String> serviceVersion = new HashMap<String, String>();

        try {
            resEnum = Thread.currentThread().getContextClassLoader().getResources(JarFile.MANIFEST_NAME);
            while (resEnum.hasMoreElements()) {
                try {
                    URL url = (URL) resEnum.nextElement();
                    InputStream is = url.openStream();
                    if (is != null) {
                        Manifest manifest = new Manifest(is);
                        Attributes mainAttribs = manifest.getMainAttributes();
                        String versionKey = mainAttribs.getValue("REMREM_VERSION_KEY");

                        if (versionKey != null) {
                            String version = mainAttribs.getValue(versionKey);
                            if (version != null) {
                                if (mainAttribs.getValue("IS_ENDPOINT_VERSION") != null) {
                                    endpointVersions.put(versionKey, version);
                                } else {
                                    serviceVersion.put(versionKey, version);
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    // Silently ignore wrong manifests on classpath?
                }
            }
            versions.put("ENDPOINT_VERSION", endpointVersions);
            versions.put("SERVICE_VERSION", serviceVersion);
        } catch (IOException e1) {
            // Silently ignore wrong manifests on classpath?
        }
        return versions;
    }
}
