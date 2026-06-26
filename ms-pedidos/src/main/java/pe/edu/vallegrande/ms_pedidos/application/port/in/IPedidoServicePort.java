package pe.edu.vallegrande.ms_pedidos.application.port.in;

import pe.edu.vallegrande.ms_pedidos.domain.model.Pedido;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPedidoServicePort {

    Flux<Pedido> findAll();

    Flux<Pedido> findByStatus(String status);

    Mono<Pedido> findById(Long id);

    Mono<Pedido> create(Pedido pedido);

    Mono<Pedido> update(Long id, Pedido pedido);

    Mono<Pedido> activate(Long id);

    Mono<Pedido> deactivate(Long id);
}
