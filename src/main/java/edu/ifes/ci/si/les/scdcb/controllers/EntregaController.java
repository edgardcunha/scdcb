package edu.ifes.ci.si.les.scdcb.controllers;

import java.util.Collection;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.ifes.ci.si.les.scdcb.model.Entrega;
import edu.ifes.ci.si.les.scdcb.services.EntregaService;
import edu.ifes.ci.si.les.scdcb.services.exceptions.ConstraintException;

@RestController
@RequestMapping(value = "/entregas")
public class EntregaController {
    
    @Autowired
    private EntregaService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Entrega>> findAll() {
        Collection<Entrega> collection = service.findAll();
        return ResponseEntity.ok().body(collection);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Entrega> find(@PathVariable Integer id) {
        Entrega obj = service.findByCdEntrega(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Entrega> insert(@Valid @RequestBody Entrega obj, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Entrega> update(@Valid @RequestBody Entrega obj, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        obj = service.update(obj);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/relatorio/{cdBeneficiado}", method = RequestMethod.GET)
    public ResponseEntity<Collection<Entrega>> findAllByBeneficiado(@PathVariable Integer cdBeneficiado) {
        Collection<Entrega> collection = service.findAllByBeneficiado(cdBeneficiado);
        return ResponseEntity.ok().body(collection);
    }

}