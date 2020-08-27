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

import edu.ifes.ci.si.les.scdcb.model.Recebimento;
import edu.ifes.ci.si.les.scdcb.services.RecebimentoService;
import edu.ifes.ci.si.les.scdcb.services.exceptions.ConstraintException;

@RestController
@RequestMapping(value = "/recebimentos")
public class RecebimentoController {
    
    @Autowired
    private RecebimentoService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Recebimento>> findAll() {
        Collection<Recebimento> collection = service.findAll();
        return ResponseEntity.ok().body(collection);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Recebimento> find(@PathVariable Integer id) {
        Recebimento obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Recebimento> insert(@Valid @RequestBody Recebimento obj, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Recebimento> update(@Valid @RequestBody Recebimento obj, BindingResult br) {
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

}