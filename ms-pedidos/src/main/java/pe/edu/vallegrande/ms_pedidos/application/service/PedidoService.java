package pe.edu.vallegrande.ms_pedidos.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.ms_pedidos.application.port.in.IPedidoServicePort;
import pe.edu.vallegrande.ms_pedidos.application.port.out.IPedidoRepositoryPort;
import pe.edu.vallegrande.ms_pedidos.application.port.out.IProductoClientPort;
import pe.edu.vallegrande.ms_pedidos.domain.model.Pedido;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PedidoService implements IPedidoServicePort {

    private final IPedidoRepositoryPort pedidoRepositoryPort;
    private final IProductoClientPort productoClientPort;

    public Flux<Pedido> findAll() {
        return pedidoRepositoryPort.findAll();
    }

    public Flux<Pedido> findByStatus(String status) {
        return pedidoRepositoryPort.findByStatus(status);
    }

    public Mono<Pedido> findById(Long id) {
        return pedidoRepositoryPort.findById(id);
    }

    public Mono<Pedido> create(Pedido pedido) {
        return productoClientPort.findById(pedido.getProductId())
                .flatMap(producto -> {
                    pedido.setTotal(producto.getPrice() * pedido.getQuantity());
                    pedido.setStatus("A");
                    pedido.setDate(LocalDateTime.now());
                    return pedidoRepositoryPort.save(pedido);
                });
    }

    public Mono<Pedido> update(Long id, Pedido pedido) {
        return pedidoRepositoryPort.findById(id)
                .flatMap(existing -> {
                    existing.setProductId(pedido.getProductId());
                    existing.setQuantity(pedido.getQuantity());
                    existing.setTotal(pedido.getTotal());
                    existing.setDate(pedido.getDate());
                    return pedidoRepositoryPort.save(existing);
                });
    }

    public Mono<Pedido> activate(Long id) {
        return pedidoRepositoryPort.findById(id)
                .flatMap(pedido -> {
                    pedido.setStatus("A");
                    return pedidoRepositoryPort.save(pedido);
                });
    }

    public Mono<Pedido> deactivate(Long id) {
        return pedidoRepositoryPort.findById(id)
                .flatMap(pedido -> {
                    pedido.setStatus("I");
                    return pedidoRepositoryPort.save(pedido);
                });
    }
}
