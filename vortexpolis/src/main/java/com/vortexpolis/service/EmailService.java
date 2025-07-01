package com.vortexpolis.service;

//import com.vortexpolis.model.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.vortexpolis.model.Compra;

import jakarta.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    public void enviarCorreoConfirmacion(String destinatario, String asunto, String contenidoHtml) {
        try {
            MimeMessage mensaje = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);

            helper.setTo(destinatario);
            helper.setSubject(asunto);
            helper.setText(contenidoHtml, true); // true -> contenido HTML

            mailSender.send(mensaje);
            logger.info("Correo enviado exitosamente a {}", destinatario);
        } catch (Exception e) {
            logger.error("Error al enviar el correo a {}: {}", destinatario, e.getMessage());
        }
    }

    public String generarContenidoCorreo(Compra compra) {
        return """
        <html>
            <head>
                <style>
                    body { font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; }
                    .container { background-color: white; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
                    h2 { color: #4CAF50; }
                    table { width: 100%; border-collapse: collapse; margin-top: 20px; }
                    table, th, td { border: 1px solid #ddd; }
                    th, td { padding: 8px; text-align: left; }
                    th { background-color: #f2f2f2; }
                    .footer { margin-top: 20px; font-size: 12px; color: #888; }
                </style>
            </head>
            <body>
                <div class="container">
                    <h2>¡Gracias por tu compra, %s!</h2>
                    <p>Tu compra ha sido registrada exitosamente. Aquí están los detalles:</p>
                    
                    <table>
                        <tr>
                            <th>ID Compra</th>
                            <th>Total</th>
                            <th>Estado</th>
                            <th>Fecha</th>
                        </tr>
                        <tr>
                            <td>%d</td>
                            <td>$%.2f</td>
                            <td>%s</td>
                            <td>%s</td>
                        </tr>
                    </table>

                    <p>¡Esperamos que disfrutes tu película!</p>
                    <div class="footer">
                        CineVortex &copy; 2025
                    </div>
                </div>
            </body>
        </html>
        """.formatted(
            compra.getCliente().getNombre(),
            compra.getId(),
            compra.getTotal(),
            compra.getEstado(),
            compra.getFecha().toString()
        );
    }

}
