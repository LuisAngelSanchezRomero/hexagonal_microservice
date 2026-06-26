package pe.edu.vallegrande.ms_pedidos.infrastructure.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.edu.vallegrande.ms_pedidos.application.port.out.IPedidoRepositoryPort;
import pe.edu.vallegrande.ms_pedidos.domain.model.Pedido;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PedidoRepositoryAdapter implements IPedidoRepositoryPort {

    private final PedidoRepository pedidoRepository;

    @Override
    public Flux<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    @Override
    public Flux<Pedido> findByStatus(String status) {
        return pedidoRepository.findByStatus(status);
    }

    @Override
    public Mono<Pedido> findById(Long id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public Mono<Pedido> save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return pedidoRepository.deleteById(id);
    }
}
