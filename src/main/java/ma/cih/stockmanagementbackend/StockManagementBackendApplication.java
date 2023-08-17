package ma.cih.stockmanagementbackend;

import ma.cih.stockmanagementbackend.entities.*;
import ma.cih.stockmanagementbackend.enums.StatusCmd;
import ma.cih.stockmanagementbackend.services.interfaces.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.stream.Stream;

@SpringBootApplication
public class StockManagementBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockManagementBackendApplication.class, args);
    }
    @Bean
    CommandLineRunner start(TypeMaterielService typeMaterielService,
                            ProprietaireService proprietaireService,
                            PrestataireService prestataireService,
                            EtablissementService etablissementService,
                            MaterielService materielService,
                            CommandeService commandeService,
                            LivraisonService livraisonService,
                            AffectationService affectationService){
        return args -> {
            Stream.of("Scanner", "Laptop", "Lecteur")
                    .forEach(type -> {
                        TypeMateriel typeMateriel = TypeMateriel.builder().name(type).build();
                        typeMaterielService.addTypeMateriel(typeMateriel);
                    });

            Stream.of("Ahmed", "Hassan", "Yassmine", "Omar")
                    .forEach(name -> {
                        Proprietaire proprietaire = Proprietaire.builder()
                                .firstName(name)
                                .lastName(name+" knia")
                                .email(name+"@gmail.com")
                                .telephone("839839030").build();
                        proprietaireService.addProprietaire(proprietaire);
                    });

            Stream.of("Ali", "Issam", "Mohamed")
                    .forEach(name -> {
                        Prestataire prestataire = Prestataire.builder()
                                .firstName(name)
                                .lastName(name+" knia")
                                .email(name+"@gmail.com")
                                .telephone("09338749").build();
                        prestataireService.addPrestataire(prestataire);
                    });

            Stream.of("Agence A", "Siege 287", "Annexe 2")
                    .forEach(name -> {
                        Etablissement etablissement = Etablissement.builder()
                                .name(name)
                                .city("Mohammedia").build();
                        etablissementService.addEtablissement(etablissement);
                    });

            Stream.of("Dell Latitude", "Oppo Scan")
                    .forEach(mat -> {
                        Materiel materiel = Materiel.builder()
                                .model(mat)
                                .numSerie(828730L)
                                .quantity(29)
                                .inventaireCih("KDL992").build();
                        materielService.addMateriel(materiel);
                    });

            Stream.of("BC383", "BC93", "BC38")
                    .forEach(bc -> {
                        Commande commande = Commande.builder()
                                .bonCmd(bc)
                                .date(LocalDate.now())
                                .quantity(3)
                                .status(StatusCmd.CREATED).build();
                        commandeService.addCommande(commande);
                    });

            Stream.of("BL04", "BL293", "BL8")
                    .forEach(bl -> {
                        Livraison livraison = Livraison.builder()
                                .bonLiv(bl)
                                .date(LocalDate.now())
                                .quantity(2).build();
                        livraisonService.addLivraison(livraison);
                    });
        };
    }
}
