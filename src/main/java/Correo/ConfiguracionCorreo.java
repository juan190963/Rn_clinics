/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Correo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

public class ConfiguracionCorreo {

    FacesContext facesContext = FacesContext.getCurrentInstance();
    ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
    String PROPERTIES_FILE = servletContext.getRealPath("/resources/correo.properties");
    private Properties properties;
    
    public ConfiguracionCorreo() {
        properties = new Properties();   
        try {            
            properties.load(new FileInputStream(PROPERTIES_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getSmtpHost() {
        return properties.getProperty("mail.smtp.host");
    }

    public int getSmtpPort() {
        return Integer.parseInt(properties.getProperty("mail.smtp.port"));
    }

    public boolean isSmtpAuth() {
        return Boolean.parseBoolean(properties.getProperty("mail.smtp.auth"));
    }

    public boolean isStartTlsEnabled() {
        return Boolean.parseBoolean(properties.getProperty("mail.smtp.starttls.enable"));
    }

    public String getSmtpUser() {
        return properties.getProperty("mail.smtp.user");
    }

    public String getSmtpPassword() {
        return properties.getProperty("mail.smtp.password");
    }
}
