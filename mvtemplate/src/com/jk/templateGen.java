package com.jk;

import java.io.*;
import java.lang.Runtime;
import java.util.Properties;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
public class templateGen {
    public static void main( String[] args ) throws Exception {
        String line;
    	try {     
    	final Process p = Runtime.getRuntime().exec("mvn archetype:generate -B -DarchetypeCatalog=local -DarchetypeGroupId=com.kohls.bigdata.omnich -DarchetypeArtifactId=of-kpi-apt-archetype -DarchetypeVersion=1.0.0-SNAPSHOT -DgroupId=com.company -DartifactId=project -Dversion=1.0-SNAPSHOT -Dpackage=com.company.project");
    	BufferedReader in = new BufferedReader(
    	           new InputStreamReader(p.getInputStream()) );
    	//BufferedWriter out = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
    	//out.write("echo hello world");

    	   while ((line = in.readLine()) != null) {
    		   	System.out.println(line);
    	     }    
    	} catch (Throwable t) {
            t.printStackTrace();
        }
    	
        VelocityEngine ve = new VelocityEngine();
        Properties props = new Properties();  
        props.put("file.resource.loader.path", "/home/exa00015/workspace/mvtemplate/src/com/jk/templates/");
        ve.init(props);
        VelocityContext context = new VelocityContext();
        context.put("name","of-kpi-mauja");
        Template t = ve.getTemplate("template.vm" );
        StringWriter writer = new StringWriter();
        t.merge( context, writer );
        try{
        FileWriter f = new FileWriter("/home/exa00015/workspace/mvtemplate/project/src/main/bin/of-kpi-apt.sh");
        BufferedWriter b = new BufferedWriter(f);
        PrintWriter p = new PrintWriter(b);
        
        	p.println(writer.toString());
        	p.close();
        }catch (IOException i) {
            i.printStackTrace();
        }
                
    }
}