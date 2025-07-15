package Backend.utnfc.isi.TPI.controller;

import Backend.utnfc.isi.TPI.models.Deposito;
import Backend.utnfc.isi.TPI.service.DepositoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/depositos")
public class DepositoController {

    @Autowired
    private DepositoService depositoService;

    @GetMapping
    public List<Deposito> listar() {
        return depositoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Deposito obtener(@PathVariable Long id) {
        return depositoService.obtenerPorId(id).orElse(null);
    }

    @PostMapping
    public Deposito crear(@RequestBody Deposito deposito) {
        return depositoService.guardar(deposito);
    }

    @PutMapping("/{id}")
    public Deposito actualizar(@PathVariable Long id, @RequestBody Deposito deposito) {
        deposito.setId(id);
        return depositoService.guardar(deposito);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        depositoService.eliminar(id);
    }
}