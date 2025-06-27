package com.example.Projeto_final_back_end;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.InputStream;

@SpringBootApplication
public class ProjetoFinalBackEndApplication {

	public static void main(String[] args) {
		try {
			InputStream serviceAccount = ProjetoFinalBackEndApplication.class
					.getClassLoader()
					.getResourceAsStream("serviceAccountKey.json");

			if (serviceAccount == null) {
				throw new RuntimeException("Arquivo 'serviceAccountKey.json' não encontrado em resources.");
			}

			serviceAccount.mark(1);
			if (serviceAccount.read() == -1) {
				throw new RuntimeException("O arquivo 'serviceAccountKey.json' está vazio.");
			}
			serviceAccount.reset();

			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.build();

			FirebaseApp.initializeApp(options);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		SpringApplication.run(ProjetoFinalBackEndApplication.class, args);
	}
}