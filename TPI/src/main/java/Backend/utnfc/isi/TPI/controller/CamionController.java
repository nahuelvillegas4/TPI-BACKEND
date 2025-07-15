package Backend.utnfc.isi.TPI.controller;

import Backend.utnfc.isi.TPI.models.Camion;
import Backend.utnfc.isi.TPI.service.CamionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/camiones")
public class CamionController {

    @Autowired
    private CamionService camionService;

    @GetMapping
    public List<Camion> listar() {
        return camionService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Camion obtener(@PathVariable Long id) {
        return camionService.obtenerPorId(id).orElse(null);
    }

    @PostMapping
    public Camion crear(@RequestBody Camion camion) {
        return camionService.guardar(camion);
    }

    @PutMapping("/{id}")
    public Camion actualizar(@PathVariable Long id, @RequestBody Camion camion) {
        camion.setId(id);
        return camionService.guardar(camion);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        camionService.eliminar(id);
    }
}
