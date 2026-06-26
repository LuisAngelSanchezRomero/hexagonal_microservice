package pe.edu.vallegrande.ms_pedidos.infrastructure.adapter.out.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import pe.edu.vallegrande.ms_pedidos.application.port.out.IProductoClientPort;
import pe.edu.vallegrande.ms_pedidos.domain.model.Producto;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductoClientAdapter implements IProductoClientPort {

    private final WebClient.Builder webClientBuilder;

    @Value("${servicios.productos-url}")
    private String productosUrl;

    @Override
    public Mono<Producto> findById(Long id) {
        return webClientBuilder.build()
                .get()
                .uri(productosUrl + "/api/v1/productos/{id}", id)
                .retrieve()
                .bodyToMono(Producto.class);
    }
}
