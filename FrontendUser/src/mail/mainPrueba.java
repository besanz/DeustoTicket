package mail;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import remote.ServiceLocator;
import remote.IRemoteFacade;
import gui.LoginUser;
import mail.*;
import data.entidades.*;
import java.util.Date;

public class mainPrueba {
    public static Ticket createTicket() {
    Evento eve =new Evento();
    eve.setTitulo("NayetSound");
    eve.setDescripcion("El festival dedicado al Nayet");
    Date fecha = new Date();
    eve.setFecha(fecha);
    eve.setCreatedAt("Fecha de creación");
    eve.setUpdatedAt("Fecha de actualización");
    eve.setPublishedAt("Fecha de publicación");
    eve.setAforo(100);

    Precio precio = new Precio();
    precio.setId(1);
    precio.setNombre("Precio regular");
    precio.setPrecio(10.0);
    precio.setDisponibles(100);
    precio.setOfertadas(0);
    // Establecer otros atributos del precio

    User user = new User();
    user.setDni("12345678A");
    user.setNombre("John");
    user.setApellidos("Doe");
    user.setEmail("john.doe@example.com");
    user.setPassword("secretpassword");
    // Establecer otros atributos del usuario

    Ticket ticket = new Ticket();
    ticket.setId(1);
    ticket.setEvento(eve);
    ticket.setPrecio(precio);
    ticket.setUser(user);
    ticket.setTitular("Titular del ticket");

    return ticket;
    }

    // Llamada al método para obtener una instancia de Ticket
    

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("uso: java [policy] [codebase] cliente.Cliente [host] [port] [server]");
            System.exit(0);
        }

        String serverIP = "127.0.0.1";
        int serverPort = 2000;
        String serverName = "GuTicketServer";

        String remoteUrl = String.format("//%s:%d/%s", serverIP, serverPort, serverName);

        // Crear una instancia de la clase EmailSender
        EmailSenderConPDFYQr emailSender = new EmailSenderConPDFYQr();

        // Aquí debes obtener los datos del ticket y los demás parámetros necesarios
        String toEmail = "benat.sanz@opendeusto.es";
        String subject = "Gracias por su compra";
        String body = "Elna ha aprendido a hacer un pdf te lo meto aqui abajo";

        Ticket ticket = createTicket(); // Reemplaza con los datos del ticket

        // Llamar al método sendEmailWithAttachment para enviar el correo electrónico con el archivo adjunto
        emailSender.sendEmailWithQR(toEmail, subject, body, ticket);
    }
}

/*String enviarA= "aritz.nieto@opendeusto.es";
        String subject="Gracias por su compra";
        String body="Gracias por comprar en guticket su entrada le llegara en cuanto el naiet sepa como hacer un pdf";
        EmailSender es =new EmailSender();
        es.sendEmail(enviarA, subject,body);*/