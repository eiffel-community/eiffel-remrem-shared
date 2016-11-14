package com.ericsson.eiffel.remrem.shared;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 * This class will search in all registered jars and their manifest file
 * for attribute Remrem-Version-Key. It will return a list with all
 * versions found.
 * 
 * @author evasiba
 *
 */
public class VersionService {

	public static Map getMessagingVersions() {
        Enumeration resEnum;
        TreeMap<String, String> versionsMap = new TreeMap<>();
        try {
            resEnum = Thread.currentThread().getContextClassLoader().getResources(JarFile.MANIFEST_NAME);
            while (resEnum.hasMoreElements()) {
                try {
                    URL url = (URL)resEnum.nextElement();                    
                    InputStream is = url.openStream();
                    if (is != null) {
                        Manifest manifest = new Manifest(is);
                        Attributes mainAttribs = manifest.getMainAttributes();
                        String versionKey = mainAttribs.getValue("Remrem-Version-Key");
                        if (versionKey != null) {
                            String version = mainAttribs.getValue(versionKey);
                            if (version != null) {
                                versionsMap.put(versionKey, version);
                            }
                        }
                    }
                }                
                catch (Exception e) {
                    // Silently ignore wrong manifests on classpath?
                }
            }
        } catch (IOException e1) {
            // Silently ignore wrong manifests on classpath?
        }
        return versionsMap; 
    }
}
