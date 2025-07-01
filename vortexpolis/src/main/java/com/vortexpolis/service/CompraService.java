package com.vortexpolis.service;

import com.vortexpolis.dto.CompraRequestDTO;
import com.vortexpolis.model.*;
import com.vortexpolis.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompraService {

    private static final Logger logger = LoggerFactory.getLogger(CompraService.class);

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FuncionRepository funcionRepository;

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private EmailService emailService;

    @Transactional
    public Compra registrarCompra(CompraRequestDTO compraRequestDTO) {

        logger.info("Iniciando proceso de compra para cliente ID: {}", compraRequestDTO.getClienteId());

        // Buscar Cliente
        Cliente cliente = clienteRepository.findById(compraRequestDTO.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        // Buscar Función
        Funcion funcion = funcionRepository.findById(compraRequestDTO.getFuncionId())
                .orElseThrow(() -> new RuntimeException("Función no encontrada"));

        // Calcular total
        double precioUnitario = funcion.getPrecioEntrada();
        double total = precioUnitario * compraRequestDTO.getCantidad();

        // Crear Compra
        Compra compra = new Compra();
        compra.setCliente(cliente);
        compra.setFecha(LocalDateTime.now());
        compra.setEstado("COMPLETADA");
        compra.setTotal(total);
        compra = compraRepository.save(compra);

        logger.info("Compra registrada con ID: {}", compra.getId());

        // Crear Entradas
        for (int i = 0; i < compraRequestDTO.getCantidad(); i++) {
            Entrada entrada = new Entrada();
            entrada.setCompra(compra);
            entrada.setFuncion(funcion);
            entrada.setPelicula(funcion.getPelicula());
            entrada.setPrecioUnitario(precioUnitario);
            entrada.setEstado("ACTIVA");
            entradaRepository.save(entrada);
        }

        logger.info("Entradas creadas para la compra ID: {}", compra.getId());

        // Crear Pago
        MetodoPago metodoPago = metodoPagoRepository.findById(compraRequestDTO.getMetodoPagoId())
                .orElseThrow(() -> new RuntimeException("Método de pago no encontrado"));

        Pago pago = new Pago();
        pago.setCompra(compra);
        pago.setMetodoPago(metodoPago);
        pago.setFechaPago(LocalDateTime.now());
        pago.setMonto(total);
        pagoRepository.save(pago);

        logger.info("Pago registrado para la compra ID: {}", compra.getId());

        // Enviar correo de confirmación con HTML
        String destinatario = cliente.getEmail();
        String asunto = "Confirmación de compra - Cine Vortex";
        String mensaje = generarContenidoCorreo(compra, compraRequestDTO.getCantidad(), funcion.getPelicula().getTitulo());

        emailService.enviarCorreoConfirmacion(destinatario, asunto, mensaje);

        logger.info("Se solicitó el envío de correo a {}", destinatario);

        return compra;
    }

    public List<Compra> consultarComprasPorCliente(Long clienteId) {
        return compraRepository.findByClienteId(clienteId);
    }

    public List<Compra> listarTodasLasCompras() {
        return compraRepository.findAll();
    }

    // Método para generar el contenido HTML
    private String generarContenidoCorreo(Compra compra, int cantidadEntradas, String tituloPelicula) {
        return """
            <html>
            <head>
                <style>
                    body { font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; }
                    .container { background-color: white; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
                    h2 { color: #4CAF50; }
                    table { width: 100%%; border-collapse: collapse; margin-top: 20px; }
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
                            <th>Película</th>
                            <th>Cantidad</th>
                            <th>Total</th>
                            <th>Estado</th>
                            <th>Fecha</th>
                        </tr>
                        <tr>
                            <td>%d</td>
                            <td>%s</td>
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
                tituloPelicula,
                cantidadEntradas,
                compra.getTotal(),
                compra.getEstado(),
                compra.getFecha().toString()
            );
    }
}
