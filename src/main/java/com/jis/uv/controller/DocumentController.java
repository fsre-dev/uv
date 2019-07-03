package com.jis.uv.controller;

import com.jis.uv.model.Document;
import com.jis.uv.model.Member;
import com.jis.uv.service.DocumentService;
import com.jis.uv.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Autowired MemberService memberService;

    private final Logger logger = LoggerFactory.getLogger(DocumentController.class);

    @GetMapping
    public ResponseEntity<List<Document>> findAll() {
        return ResponseEntity.ok(documentService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<Document>> findAllByMember(@PathVariable Long id) {
        Member member = memberService.findById(id);
        if (member == null) {
            logger.error("Member with id {} not found", id);
            return ResponseEntity.notFound().build();
        }
        logger.info("Documents for member with id {} founded", id);
        return ResponseEntity.ok(documentService.findAllByMember(member));
    }

    @PostMapping
    public ResponseEntity<Document> create(@RequestBody Document document) {

        Document createdDocument = documentService.createDocument(document);
        logger.info("Document has been created, id : {}", createdDocument.getId());
        return ResponseEntity.ok(createdDocument);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Document> update(@RequestBody Document document, @PathVariable(value = "id") Long id) {
        Document updatedDocument = documentService.findById(id);
        if (updatedDocument == null) {
            logger.error("Unable to find document with id {}", updatedDocument.getId());
            return ResponseEntity.notFound().build();
        }

        updatedDocument = documentService.updateDocument(document, id);
        logger.info("Document has been updated, id : {}", updatedDocument.getId());
        return ResponseEntity.ok(updatedDocument);
    }

    @PutMapping(value = "/delete/{id}")
    public ResponseEntity<Document> delete(@PathVariable(value = "id") Long id) {
        try {
            Document deletedDocument = documentService.deleteDocument(id);
            logger.info("Document has been deleted, id : {}", deletedDocument.getId());
            return ResponseEntity.ok(deletedDocument);
        } catch (Exception e) {
            logger.error("Unable to delete document, id : {}", id);
            return ResponseEntity.notFound().build();
        }
    }
}
