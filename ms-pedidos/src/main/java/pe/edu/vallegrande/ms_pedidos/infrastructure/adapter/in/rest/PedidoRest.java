package pe.edu.vallegrande.ms_pedidos.infrastructure.adapter.in.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.ms_pedidos.application.port.in.IPedidoServicePort;
import pe.edu.vallegrande.ms_pedidos.domain.model.Pedido;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/pedidos")
@RequiredArgsConstructor
public class PedidoRest {

    private final IPedidoServicePort pedidoService;

    @GetMapping
    public Flux<Pedido> findAll() {
        return pedidoService.findAll();
    }

    @GetMapping("/status/{status}")
    public Flux<Pedido> findByStatus(@PathVariable String status) {
        return pedidoService.findByStatus(status);
    }

    @GetMapping("/{id}")
    public Mono<Pedido> findById(@PathVariable Long id) {
        return pedidoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Pedido> create(@RequestBody Pedido pedido) {
        return pedidoService.create(pedido);
    }

    @PutMapping("/{id}")
    public Mono<Pedido> update(@PathVariable Long id, @RequestBody Pedido pedido) {
        return pedidoService.update(id, pedido);
    }

    @PutMapping("/{id}/activate")
    public Mono<Pedido> activate(@PathVariable Long id) {
        return pedidoService.activate(id);
    }

    @PutMapping("/{id}/deactivate")
    public Mono<Pedido> deactivate(@PathVariable Long id) {
        return pedidoService.deactivate(id);
    }
}
