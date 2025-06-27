package com.example.Projeto_final_back_end.repository;

import com.example.Projeto_final_back_end.exception.FirebaseOperationException;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Repository
public abstract class FirebaseRepository<T> {

    private final String collectionName;
    private final Firestore db = FirestoreClient.getFirestore();

    protected FirebaseRepository(String collectionName) {
        this.collectionName = collectionName;
    }

    public void save(String id, T entity) {
        System.out.println("Salvando no Firebase coleção: " + collectionName + ", id: " + id);
        try {
            ApiFuture<WriteResult> future = db.collection(collectionName).document(id).set(entity);
            WriteResult result = future.get(); // espera até finalizar
            System.out.println("Salvo com sucesso em: " + result.getUpdateTime());
        } catch (ExecutionException | InterruptedException e) {
            System.err.println("Erro ao salvar no Firebase: " + e.getMessage());
            e.printStackTrace();
            Thread.currentThread().interrupt(); // boa prática para InterruptedException
            throw new FirebaseOperationException("Erro ao salvar no Firebase", e);
        }
    }

    public T findById(String id, Class<T> clazz) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection(collectionName).document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            return document.toObject(clazz);
        }
        return null;
    }

    public List<T> findAll(Class<T> clazz) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = db.collection(collectionName).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        return documents.stream().map(doc -> doc.toObject(clazz)).toList();
    }

    public void deleteById(String id) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = db.collection(collectionName).document(id).delete();
        future.get();
    }
}