package sistema_alertas.Alertas.model;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class ServicioSms {

    private static final String SID_CUENTA = "oculto";
    private static final String TOKEN_AUTORIZACION = "coulto";
    private static final String NUMERO_TWILIO = "oculto";

    public static String enviarMensaje(String numeroDestino, String textoMensaje) {
        System.out.println("Accediendo al metodo enviarMensaje()");

        try {
            Twilio.init(SID_CUENTA, TOKEN_AUTORIZACION);

            if (!numeroDestino.startsWith("+")) {
                numeroDestino = "+57" + numeroDestino;
            }

            Message mensaje = Message.creator(
                    new PhoneNumber(numeroDestino),
                    new PhoneNumber(NUMERO_TWILIO),
                    textoMensaje
            ).create();

            String sid = mensaje.getSid();
            System.out.println(" Mensaje enviado correctamente. SID: " + sid);
            return "Mensaje enviado correctamente. SID: " + sid;

        } catch (Exception e) {
            System.out.println("Error al enviar el mensaje: " + e.getMessage());
            return "Error al enviar mensaje: " + e.getMessage();
        }
    }
}
