package com.prueba.prueba.persistence;

import com.prueba.prueba.domain.repository.ProductRepository;
import com.prueba.prueba.domain.Product;
import com.prueba.prueba.persistence.crud.ProductoCrudRepository;
import com.prueba.prueba.persistence.entity.Producto;
import com.prueba.prueba.persistence.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {

    private ProductoCrudRepository pCrud;
    private ProductMapper mapper;

    public List<Product> getAll(){
        List<Producto> productos = (List<Producto>) pCrud.findAll();
        //La funcion necesita
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = pCrud.findByIdCategoriaOrderByNombreAsc(categoryId);
        //se usa Optional.of para convertir la lista de productos a un Valor Optional, por que la funcoinaespera un valor Optional.
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScaredProduct(int quantity) {
        Optional<List<Producto>> productos = pCrud.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prod -> mapper.toProducts(prod));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return pCrud.findById(productId).map(prod -> mapper.toProduct(prod));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(pCrud.save(producto));
    }

    public Optional<List<Producto>> getGreaterPrices(int precioTope){
        return pCrud.findByPrecioVentaGreaterThan(precioTope);
    }


    @Override
    public void delete(int productId){
        pCrud.deleteById(productId);
    }
//    public Optional<Producto> getProductoById(int idProducto){
//        return pCrud.findById(idProducto);
//    }
}
