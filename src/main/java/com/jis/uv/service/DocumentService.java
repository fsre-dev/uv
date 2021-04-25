package com.jis.uv.service;

import com.jis.uv.model.Document;
import com.jis.uv.model.Member;
import com.jis.uv.repository.DocumentRepository;
import excelExport.DocumentExcelExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;


@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;


    public List<Document> findAll() {
        return documentRepository.findAll();
    }

    public Page<Document> findAllDynamic(Specification<Document> specification, Pageable pageRequest) {
        return documentRepository.findAll(specification, pageRequest);
    }

    public List<Document> findAllByMember(Member member) {
        return documentRepository.findAllByMembers(member);
    }

    public Document findById(Long id) {
        return documentRepository.findById(id).orElse(null);
    }

    public Document createDocument(Document document) {
        document.setDeleted(false);

        return documentRepository.save(document);
    }

    public Document updateDocument(Document document, Long id) {
        document.setId(id);
        return documentRepository.save(document);
    }

    public Document deleteDocument(Long id) throws Exception {
        Document document = documentRepository.findById(id).orElse(null);
        if (document == null) {
            throw new Exception("Can't delete. Document does not exists. Id : " + id);
        }
        document.setDeleted(true);
        return documentRepository.save(document);
    }

    public void exportDocument(Document document, HttpServletResponse response) throws IOException {
        DocumentExcelExporter documentExcelExporter = new DocumentExcelExporter(document);
        documentExcelExporter.export(response);
    }

}
