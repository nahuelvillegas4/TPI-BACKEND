package Backend.utnfc.isi.TPI.controller;

import Backend.utnfc.isi.TPI.models.Contenedor;
import Backend.utnfc.isi.TPI.service.ContenedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contenedores")
public class ContenedorController {

    @Autowired
    private ContenedorService contenedorService;

    @GetMapping
    public List<Contenedor> listar() {
        return contenedorService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Contenedor obtener(@PathVariable Long id) {
        return contenedorService.obtenerPorId(id).orElse(null);
    }

    @PostMapping
    public Contenedor crear(@RequestBody Contenedor contenedor) {
        return contenedorService.guardar(contenedor);
    }

    @PutMapping("/{id}")
    public Contenedor actualizar(@PathVariable Long id, @RequestBody Contenedor contenedor) {
        contenedor.setId(id);
        return contenedorService.guardar(contenedor);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        contenedorService.eliminar(id);
    }
}