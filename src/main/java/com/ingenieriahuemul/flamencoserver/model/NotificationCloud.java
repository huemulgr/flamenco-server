package com.ingenieriahuemul.flamencoserver.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ingenieriahuemul.flamencoserver.model.dto.EstadoMasDTO;

public class NotificationCloud {

	
	public static void main(String[] args) {

		try {

			FileInputStream serviceAccount = new FileInputStream("/clave/serviceAccountKey-prod.json");

			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setDatabaseUrl("https://flamenco-1410a.firebaseio.com/").build();
			FirebaseApp.initializeApp(options);

			// As an admin, the app has access to read and write all data, regardless of Security Rules
			DatabaseReference ref = FirebaseDatabase.getInstance().getReference("ROOT");

			DatabaseReference usersRef = ref.child("QueueMAS");

			Map<String, Object> listMas = new HashMap<String, Object>();
			
			EstadoMasDTO estadoMasDto = new EstadoMasDTO();
			
			estadoMasDto.setId("Id-00001");
			estadoMasDto.setLongName("Nombre largo del MAS");
			estadoMasDto.setShortName("Nombre corto del MAS");
			estadoMasDto.setValue("-2.17");
			
			listMas.put(estadoMasDto.getId(), estadoMasDto);

			//usersRef.setValueAsync(listMas);
			usersRef.updateChildrenAsync(listMas);
		
			Thread.sleep(70000L);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
