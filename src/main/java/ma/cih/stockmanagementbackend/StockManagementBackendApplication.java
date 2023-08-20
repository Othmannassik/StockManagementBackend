package ma.cih.stockmanagementbackend;

import ma.cih.stockmanagementbackend.dtos.*;
import ma.cih.stockmanagementbackend.enums.StatusCmd;
import ma.cih.stockmanagementbackend.exceptions.*;
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
                        TypeMaterielDTO typeMaterielDTO = new TypeMaterielDTO();
                        typeMaterielDTO.setName(type);
                        typeMaterielService.addTypeMateriel(typeMaterielDTO);
                    });

            Stream.of("Ahmed", "Hassan", "Yassmine", "Omar")
                    .forEach(name -> {
                        ProprietaireDTO proprietaireDTO = new ProprietaireDTO();
                        proprietaireDTO.setFirstName(name);
                        proprietaireDTO.setLastName(name+" knia");
                        proprietaireDTO.setEmail(name+"@gmail.com");
                        proprietaireDTO.setTelephone("03837376");
                        proprietaireService.addProprietaire(proprietaireDTO);
                    });

            Stream.of("Ali", "Issam", "Mohamed")
                    .forEach(name -> {
                        PrestataireDTO prestataireDTO = new PrestataireDTO();
                        prestataireDTO.setFirstName(name);
                        prestataireDTO.setLastName(name+" knia");
                        prestataireDTO.setEmail(name+"@gmail.com");
                        prestataireDTO.setTelephone("03837376");
                        prestataireService.addPrestataire(prestataireDTO);
                    });

            Stream.of("Agence A", "Siege 287", "Annexe 2")
                    .forEach(name -> {
                        EtablissementDTO etablissementDTO = new EtablissementDTO();
                        etablissementDTO.setName(name);
                        etablissementDTO.setCity("Casa");
                        etablissementService.addEtablissement(etablissementDTO);
                    });

            Stream.of("Dell Latitude", "Oppo Scan")
                    .forEach(mat -> {
                        MaterielDTO materielDTO = new MaterielDTO();
                        materielDTO.setModel(mat);
                        materielDTO.setQuantity(29);
                        materielDTO.setNumSerie(828730L);
                        materielDTO.setInventaireCih("KDL992");
                        try {
                            materielDTO.setTypeMaterielDTO(typeMaterielService.findTypeMateriel(2L));
                            materielDTO.setEtablissementDTO(etablissementService.findEtablissement(1L));
                        } catch (TypeMaterielNotFoundException | EtablissementNotFoundException e) {
                            e.printStackTrace();
                        }
                        materielService.addMateriel(materielDTO);
                    });

            Stream.of("BC383", "BC93", "BC38")
                    .forEach(bc -> {
                        CommandeDTO commandeDTO = new CommandeDTO();
                        commandeDTO.setDate(LocalDate.now());
                        commandeDTO.setBonCmd(bc);
                        commandeDTO.setQuantity(4);
                        try {
                            commandeDTO.setMaterielDTO(materielService.findMateriel(1L));
                            commandeDTO.setPrestataireDTO(prestataireService.findPrestataire(2L));
                        } catch (MaterielNotFoundException | PrestataireNotFoundException e) {
                            e.printStackTrace();
                        }
                        commandeDTO.setStatus(StatusCmd.CREATED);
                        commandeService.addCommande(commandeDTO);
                    });

            Stream.of("BL04", "BL293", "BL8")
                    .forEach(bl -> {
                        LivraisonDTO livraisonDTO = new LivraisonDTO();
                        livraisonDTO.setDate(LocalDate.now());
                        livraisonDTO.setQuantity(3);
                        livraisonDTO.setBonLiv(bl);
                        try {
                            livraisonDTO.setCommandeDTO(commandeService.findCommande(2L));
                        } catch (CommandeNotFoundException e) {
                            e.printStackTrace();
                        }
                        livraisonService.addLivraison(livraisonDTO);
                    });


            Stream.of("motif 1", "motif 2", "motif 3")
                    .forEach(motif -> {
                        AffectationDTO affectationDTO = new AffectationDTO();
                        affectationDTO.setDate(LocalDate.now());
                        affectationDTO.setMotif(motif);
                        try {
                            affectationDTO.setProprietaireDTO(proprietaireService.findProprietaire(2L));
                            affectationDTO.setMaterielDTO(materielService.findMateriel(1L));
                        } catch (ProprietaireNotFoundException | MaterielNotFoundException e) {
                            e.printStackTrace();
                        }
                        affectationService.addAffectation(affectationDTO);
                    });
        };
    }
}
