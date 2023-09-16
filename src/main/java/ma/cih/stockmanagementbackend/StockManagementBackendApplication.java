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
    //@Bean
    CommandLineRunner start(TypeMaterielService typeMaterielService,
                            ProprietaireService proprietaireService,
                            PrestataireService prestataireService,
                            EtablissementService etablissementService,
                            MaterielService materielService,
                            CommandeService commandeService,
                            LivraisonService livraisonService,
                            AffectationService affectationService,
                            MaterielDetailService materielDetailService){
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

            Stream.of("Electro Planet", "Fnac", "Societe x")
                    .forEach(name -> {
                        PrestataireDTO prestataireDTO = new PrestataireDTO();
                        prestataireDTO.setRaisonSocial(name);
                        prestataireDTO.setEmail(name+"@gmail.com");
                        prestataireDTO.setTelephone("03837376");
                        prestataireService.addPrestataire(prestataireDTO);
                    });

            Stream.of("Agence A", "Siege 287", "Annexe 2")
                    .forEach(name -> {
                        EtablissementDTO etablissementDTO = new EtablissementDTO();
                        etablissementDTO.setName(name);
                        etablissementDTO.setAdresse("This is an adresse");
                        etablissementDTO.setCity("Casa");
                        etablissementService.addEtablissement(etablissementDTO);
                    });

            Stream.of("Dell Latitude", "Oppo Scan")
                    .forEach(mat -> {
                        MaterielDTO materielDTO = new MaterielDTO();
                        materielDTO.setModel(mat);
                        materielDTO.setQuantity(4);
                        try {
                            materielDTO.setTypeMateriel(typeMaterielService.findTypeMateriel(2L));
                        } catch (TypeMaterielNotFoundException e) {
                            e.printStackTrace();
                        }
                        materielService.addMateriel(materielDTO);
                    });

            Stream.of("BC383", "BC93", "BC38")
                    .forEach(bc -> {
                        CommandeDTO commandeDTO = new CommandeDTO();
                        commandeDTO.setDate(LocalDate.now());
                        commandeDTO.setNumBonCmd(bc);
                        commandeDTO.setQuantity(4);
                        commandeDTO.setStatus(StatusCmd.CREATED);
                        
                        try {
                            commandeDTO.setPrestataire(prestataireService.findPrestataire(2L));
                            commandeDTO.setMateriel(materielService.findMateriel(2L));
                            commandeDTO.setEtablissement(etablissementService.findEtablissement(3L));
                        } catch (PrestataireNotFoundException | MaterielNotFoundException | EtablissementNotFoundException e) {
                            e.printStackTrace();
                        }

                        /* for (int i = 0; i < commandeDTO.getQuantity(); i++) {
                            MaterielDetailDTO materielDetailDTO = new MaterielDetailDTO();
                            materielDetailDTO.setNumSerie("JSKJ7872");
                            materielDetailDTO.setInventaireCih("Los298");
                            materielDetailDTO.setMateriel(commandeDTO.getMateriel());
                        }*/

                        commandeService.addCommande(commandeDTO);
                    });

            Stream.of("BL04", "BL293", "BL8")
                    .forEach(bl -> {
                        LivraisonDTO livraisonDTO = new LivraisonDTO();
                        livraisonDTO.setDate(LocalDate.now());
                        livraisonDTO.setQuantity(3);
                        livraisonDTO.setBonLiv(bl);
                        try {
                            livraisonService.addLivraison(livraisonDTO, 2L);
                        } catch (CommandeNotFoundException e) {
                            e.printStackTrace();
                        }
                    });


            Stream.of("motif 1", "motif 2", "motif 3")
                    .forEach(motif -> {
                        AffectationDTO affectationDTO = new AffectationDTO();
                        affectationDTO.setDate(LocalDate.now());
                        affectationDTO.setMotif(motif);
                        try {
                            affectationDTO.setProprietaireDTO(proprietaireService.findProprietaire(2L));
                            affectationDTO.setMaterielDetailDTO(materielDetailService.findMaterielDetail(1L));
                        } catch (ProprietaireNotFoundException | MaterielDetailNotFoundException e) {
                            e.printStackTrace();
                        }
                        affectationService.addAffectation(affectationDTO);
                    });
        };
    }
}
