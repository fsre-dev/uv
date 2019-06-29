package com.jis.uv.controller;

import com.jis.uv.model.Document;
import com.jis.uv.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping
    public ResponseEntity<List<Document>> findAll() {
        return ResponseEntity.ok(documentService.findAll());
    }

    @PostMapping
    public ResponseEntity<Document> create(@RequestBody Document document) {

            Document createdDocument = documentService.createDocument(document);
            return ResponseEntity.ok(createdDocument);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Document> update(@RequestBody Document document, @PathVariable(value = "id") Long id){
        Document updatedDocument = documentService.updateDocument(document,id);

        return ResponseEntity.ok(updatedDocument);
    }

}
