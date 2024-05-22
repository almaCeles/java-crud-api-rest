
package com.apirest.apirest.Controllers;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.apirest.Entities.Producto;
import com.apirest.apirest.Repositories.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository ;

    @GetMapping
    public List<Producto> getAllProduct(){
        return productoRepository.findAll();
    }
    
    @PostMapping
    public Producto CreateProduct(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    @GetMapping("/{id}")
    public Producto getProductId(@RequestParam Long id) {
        return  productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro el producto"+ id)); 
    }

    @PutMapping("/{id}")
    public Producto updateProduct(@RequestParam Long id, @RequestBody Producto detallesProducto) {
        Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro el producto"+ id)); 

        
        producto.setNombre(detallesProducto.getNombre());
        producto.setPrecio(detallesProducto.getPrecio());

        return productoRepository.save(producto);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@RequestParam Long id){
        Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro el producto"+ id)); 

        productoRepository.delete(producto);
        return "El producto con el ID:" + id + " fue eliminado correctamente";
    }
}
