package com.pcer2.service_estadisticas.service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pcer2.service_estadisticas.dto.*;
import com.pcer2.service_estadisticas.model.Estadistica;
import com.pcer2.service_estadisticas.repository.EstadisticaRepository;

@Service
public class EstadisticasService {

        // URIS de los otros ms. todo aquí.

    private static final String CLIENTES_URI =
            "http://localhost:8081/api/v1/clientes";

    private static final String EQUIPOS_URI =
            "http://localhost:8082/api/v1/equipos";

    private static final String VOUCHER_URI =
            "http://localhost:8085/api/v1/vouchers";

        private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private EstadisticaRepository repository;

    @Autowired
    private WebClient webClient;

    public Estadistica generarReporteClientes() throws Exception {

        List<ClienteDTO> clientes =
                Arrays.asList(
                        webClient.get()
                                .uri(CLIENTES_URI)
                                .retrieve()
                                .bodyToMono(ClienteDTO[].class)
                                .block()
                );

        ClienteDTO masFiel =
                clientes.stream()
                        .max(Comparator.comparingInt(
                                ClienteDTO::getOrdenes_totales))
                        .orElse(null);

        ClienteDTO masEquipos =
                clientes.stream()
                        .max(Comparator.comparingInt(
                                c -> c.getEquipos().size()))
                        .orElse(null);

        ClienteDTO masAntiguo =
                clientes.stream()
                        .min(Comparator.comparing(
                                ClienteDTO::getFecha_registro))
                        .orElse(null);

        ClienteDTO ultimo =
                clientes.stream()
                        .max(Comparator.comparing(
                                ClienteDTO::getFecha_registro))
                        .orElse(null);

        ReporteClientesDTO reporte = new ReporteClientesDTO(

                masFiel.getNombre() + " " + masFiel.getApellido(),
                masEquipos.getNombre() + " " + masEquipos.getApellido(),
                masAntiguo.getNombre() + " " + masAntiguo.getApellido(),
                ultimo.getNombre() + " " + ultimo.getApellido(),
                masFiel.getOrdenes_totales(),
                masEquipos.getEquipos() != null
                        ? masEquipos.getEquipos().size()
                        : 0
        );

        Estadistica estadistica = new Estadistica();

        estadistica.setCategoria("CLIENTES");
        estadistica.setFechaGeneracion(LocalDateTime.now());
        estadistica.setReporteJson(
                objectMapper.writerWithDefaultPrettyPrinter()
                        .writeValueAsString(reporte));

        return repository.save(estadistica);
    }

    public Estadistica generarReporteEquipos() throws Exception {

        List<EquipoDTO> equipos =
                Arrays.asList(
                        webClient.get()
                                .uri(EQUIPOS_URI)
                                .retrieve()
                                .bodyToMono(EquipoDTO[].class)
                                .block()
                );

        String tipoMasComun =
                equipos.stream()
                        .collect(Collectors.groupingBy(
                                EquipoDTO::getTipoEquipo,
                                Collectors.counting()))
                        .entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue())
                        .get()
                        .getKey();

        String marcaMasComun =
                equipos.stream()
                        .collect(Collectors.groupingBy(
                                EquipoDTO::getMarca,
                                Collectors.counting()))
                        .entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue())
                        .get()
                        .getKey();

        String almacenMasComun =
                equipos.stream()
                        .collect(Collectors.groupingBy(
                                EquipoDTO::getTipoAlmacen,
                                Collectors.counting()))
                        .entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue())
                        .get()
                        .getKey();

        Long equipoMasReparado =
                equipos.stream()
                        .max(Comparator.comparingInt(
                                EquipoDTO::getVeces_reparado))
                        .get()
                        .getId();

        ReporteEquiposDTO reporte =
                new ReporteEquiposDTO(
                        tipoMasComun,
                        marcaMasComun,
                        almacenMasComun,
                        equipoMasReparado
                );

        Estadistica estadistica = new Estadistica();

        estadistica.setCategoria("EQUIPOS");
        estadistica.setFechaGeneracion(LocalDateTime.now());
        estadistica.setReporteJson(
                objectMapper.writerWithDefaultPrettyPrinter()
                        .writeValueAsString(reporte));

        return repository.save(estadistica);
    }

    public Estadistica generarReporteVoucher() throws Exception {

        List<VoucherDTO> vouchers =
                Arrays.asList(
                        webClient.get()
                                .uri(VOUCHER_URI)
                                .retrieve()
                                .bodyToMono(VoucherDTO[].class)
                                .block()
                );

        String metodoMasUsado =
                vouchers.stream()
                        .collect(Collectors.groupingBy(
                                VoucherDTO::getMetodoPago,
                                Collectors.counting()))
                        .entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue())
                        .get()
                        .getKey();

        Double ticketPromedio =
                vouchers.stream()
                        .mapToDouble(VoucherDTO::getTotal)
                        .average()
                        .orElse(0);

        ReporteVoucherDTO reporte =
                new ReporteVoucherDTO(
                        metodoMasUsado,
                        ticketPromedio
                );

        Estadistica estadistica = new Estadistica();

        estadistica.setCategoria("VOUCHERS");
        estadistica.setFechaGeneracion(LocalDateTime.now());

        estadistica.setReporteJson(
                objectMapper.writerWithDefaultPrettyPrinter()
                        .writeValueAsString(reporte));

        return repository.save(estadistica);
    }

    public List<Estadistica> listar() {
        return repository.findAll();
    }

    public Optional<Estadistica> buscar(Long id) {
        return repository.findById(id);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}