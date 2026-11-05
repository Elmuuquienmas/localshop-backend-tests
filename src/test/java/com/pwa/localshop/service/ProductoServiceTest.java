package com.pwa.localshop.service;

import com.pwa.localshop.model.Producto;
import com.pwa.localshop.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    private Producto producto;

    @BeforeEach
    void setUp() {
        producto = new Producto(1L, "Cafetera Espresso", "Cafetera de 15 bares", 1250.00, "/images/cafe.png");
    }

    @Test
    @DisplayName("Prueba unitaria para listar todos los productos (findAll)")
    void testFindAllProductos() {
        // Given (Dado un entorno con datos simulados)
        Producto producto2 = new Producto(2L, "Licuadora", "Licuadora de 3 velocidades", 850.00, "/images/licuadora.png");
        given(productoRepository.findAll()).willReturn(Arrays.asList(producto, producto2));

        // When (Cuando se ejecuta el método)
        List<Producto> productosList = productoService.findAll();

        // Then (Entonces se validan los resultados)
        assertThat(productosList).isNotNull();
        assertThat(productosList.size()).isEqualTo(2);
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Prueba unitaria para crear y guardar un producto (create)")
    void testCreateProducto() {
        // Given
        given(productoRepository.save(any(Producto.class))).willReturn(producto);

        // When
        Producto productoGuardado = productoService.save(producto);

        // Then
        assertThat(productoGuardado).isNotNull();
        assertThat(productoGuardado.getNombre()).isEqualTo("Cafetera Espresso");
        assertThat(productoGuardado.getPrecio()).isGreaterThan(0.0);
        verify(productoRepository, times(1)).save(producto);
    }
}
