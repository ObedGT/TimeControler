/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parcial.parcialito;

import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EnviarCorreo {
    
    public boolean enviarCorreo(Correo c){
        try{
            Properties p = new Properties();
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.setProperty("mail.smtp.starttls.enable","true");
            p.setProperty("mail.smtp.port", "587");
            p.setProperty("mail.smtp.user", c.getUsuarioCorreo());
            p.setProperty("mail.smtp.auth", "true");
            
            Session s = Session.getDefaultInstance(p,null);
            BodyPart texto = new MimeBodyPart();
            texto.setText(c.getMensaje());
            
            /*BodyPart adjunto= new MimeBodyPart();
            
            if(c.getRutaArchivo().equals("")){
                adjunto.setDataHandler(new DataHandler(new FileDataSource(c.getRutaArchivo())));
                adjunto.setFileName(c.getNombreArchivo());
            }
            */
            MimeMultipart m = new MimeMultipart();
            m.addBodyPart(texto);
            /*
            if(!c.getRutaArchivo().equals("")){
                m.addBodyPart(adjunto);
            }
*/
            MimeMessage message= new MimeMessage(s);
            message.setFrom(new InternetAddress(c.getUsuarioCorreo()));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(c.getDestino()));
            message.setSubject(c.getAsunto());
            message.setContent(m);
            
            Transport t =s.getTransport("smtp");
            t.connect(c.getUsuarioCorreo(),c.getPass());
            t.sendMessage(message, message.getAllRecipients());
            t.close();
            return true;
        }
        catch(Exception e){
            System.out.println("Error "+e);
            return false;
        }
        
    }
    
}
