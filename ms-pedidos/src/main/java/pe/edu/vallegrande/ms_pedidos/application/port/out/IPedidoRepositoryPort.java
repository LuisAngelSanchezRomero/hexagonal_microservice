package pe.edu.vallegrande.ms_pedidos.application.port.out;

import pe.edu.vallegrande.ms_pedidos.domain.model.Pedido;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPedidoRepositoryPort {

    Flux<Pedido> findAll();

    Flux<Pedido> findByStatus(String status);

    Mono<Pedido> findById(Long id);

    Mono<Pedido> save(Pedido pedido);

    Mono<Void> deleteById(Long id);
}
