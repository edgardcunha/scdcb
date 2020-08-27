package edu.ifes.ci.si.les.scdcb.controllers;

import java.util.Collection;
import javax.validation.Valid;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import edu.ifes.ci.si.les.scdcb.model.Solicitacao;
import edu.ifes.ci.si.les.scdcb.services.SolicitacaoService;
import edu.ifes.ci.si.les.scdcb.services.exceptions.ConstraintException;

@RestController
@RequestMapping(value = "/solicitacoes")
public class SolicitacaoController {
    
    @Autowired
    private SolicitacaoService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Solicitacao>> findAll() {
        Collection<Solicitacao> collection = service.findAll();
        return ResponseEntity.ok().body(collection);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Solicitacao> find(@PathVariable Integer id) {
        Solicitacao obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Solicitacao> insert(@Valid @RequestBody Solicitacao obj, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Solicitacao> update(@Valid @RequestBody Solicitacao obj, BindingResult br) {
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

    @RequestMapping(value = "/data/{data}", method = RequestMethod.GET)
    public ResponseEntity<Collection<Solicitacao>> findAllByDtSolicitacao(@RequestParam(value="data", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dtSolicitacao) {
        Collection<Solicitacao> collection = service.findAllByDtSolicitacao(dtSolicitacao);
        return ResponseEntity.ok().body(collection);
    }


}