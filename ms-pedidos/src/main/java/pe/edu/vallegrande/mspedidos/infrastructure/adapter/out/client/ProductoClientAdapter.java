package pe.edu.vallegrande.mspedidos.infrastructure.adapter.out.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import pe.edu.vallegrande.mspedidos.application.port.out.IProductoClientPort;
import pe.edu.vallegrande.mspedidos.domain.model.Producto;
import reactor.core.publisher.Mono;

@Component
public class ProductoClientAdapter implements IProductoClientPort {

    private final WebClient webClient;

    public ProductoClientAdapter(@Value("${spring.servicios.productos-url:http://localhost:8081}") String productosUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(productosUrl)
                .build();
    }

    @Override
    public Mono<Producto> findById(Long id) {
        return webClient.get()
                .uri("/api/productos/{id}", id)
                .retrieve()
                .bodyToMono(Producto.class);
    }

    @Override
    public Mono<Producto> decreaseStock(Long id, Integer quantity) {
        return webClient.patch()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/productos/{id}/decreaseStock")
                        .queryParam("quantity", quantity)
                        .build(id))
                .retrieve()
                .bodyToMono(Producto.class);
    }
}
