package Backend.utnfc.isi.TPI.controller;

import Backend.utnfc.isi.TPI.models.Ciudad;
import Backend.utnfc.isi.TPI.service.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ciudades")
public class CiudadController {

    @Autowired
    private CiudadService ciudadService;

    @GetMapping
    public List<Ciudad> listar() {
        return ciudadService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Ciudad obtener(@PathVariable Long id) {
        return ciudadService.obtenerPorId(id).orElse(null);
    }

    @PostMapping
    public Ciudad crear(@RequestBody Ciudad ciudad) {
        return ciudadService.guardar(ciudad);
    }

    @PutMapping("/{id}")
    public Ciudad actualizar(@PathVariable Long id, @RequestBody Ciudad ciudad) {
        ciudad.setId(id);
        return ciudadService.guardar(ciudad);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        ciudadService.eliminar(id);
    }
}