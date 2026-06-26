package pe.edu.vallegrande.ms_pedidos.infrastructure.adapter.out.persistence;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import pe.edu.vallegrande.ms_pedidos.domain.model.Pedido;
import reactor.core.publisher.Flux;

@Repository
public interface PedidoRepository extends ReactiveCrudRepository<Pedido, Long> {

    Flux<Pedido> findByStatus(String status);
}
