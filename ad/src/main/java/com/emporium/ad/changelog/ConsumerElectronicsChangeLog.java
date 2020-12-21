package com.emporium.ad.changelog;

import com.emporium.ad.model.jpa.Category;
import com.kuliginstepan.mongration.annotation.Changelog;
import com.kuliginstepan.mongration.annotation.Changeset;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Set;

@Changelog
public class ConsumerElectronicsChangeLog {

    @Changeset(order = 1, id = "initElectronic", author = "afakherutdinov")
    public void electronicInit(MongoTemplate mongoTemplate) {
        Category category = new Category("Consumer Electronics");

        Category audioVideo = new Category("Audio and video", Set.of(
                new Category("MP3 players"),
                new Category("Acoustics, speakers, subwoofers"),
                new Category("Video, DVD and Blu-ray players"),
                new Category("Camcorders"),
                new Category("Cables and adapters"),
                new Category("Microphones"),
                new Category("Music and films"),
                new Category("Music centers, radio tape recorders"),
                new Category("Headphones"),
                new Category("Amplifiers and Receivers"),
                new Category("Accessories"),
                new Category("TVs and Projectors", Set.of(
                        new Category("TV sets"),
                        new Category("Projectors"),
                        new Category("Other")
                ))
        ));

        Category games = new Category("Games, consoles and programs", Set.of(
                new Category("Games for consoles"),
                new Category("Gaming consoles"),
                new Category("Computer games"),
                new Category("Programs")
        ));

        Category desktops = new Category("Desktop computers");
        Category laptops = new Category("Laptops");
        Category officeEquipment = new Category("Office equipment and consumables", Set.of(
                new Category("Printers"),
                new Category("Telephony"),
                new Category("Paper shredders"),
                new Category("Chancery"),
                new Category("MFPs, Copiers and Scanners", Set.of(
                        new Category("IFI"),
                        new Category("Copiers"),
                        new Category("Scanners"),
                        new Category("Other")
                )),
                new Category("UPS, surge protectors", Set.of(
                        new Category("UPS"),
                        new Category("Surge Protectors"),
                        new Category("Network filters"),
                        new Category("Other")
                )),
                new Category("Expendable materials", Set.of(
                        new Category("Power supplies and batteries"),
                        new Category("Blanks"),
                        new Category("Paper"),
                        new Category("Cables and adapters"),
                        new Category("Cartridges")
                ))
        ));

        Category tablets = new Category("Tablets and e-books", Set.of(
                new Category("Tablets"),
                new Category("EBooks"),
                new Category("Accessories", Set.of(
                        new Category("Batteries"),
                        new Category("Headsets and headphones"),
                        new Category("Docking stations"),
                        new Category("Charging device"),
                        new Category("Cables and adapters"),
                        new Category("Modems and routers"),
                        new Category("Styluses"),
                        new Category("Covers and tapes"),
                        new Category("Other")
                ))
        ));

        Category phones = new Category("Phones", Set.of(
                new Category("Acer"),
                new Category("HTC"),
                new Category("LG"),
                new Category("Huawei"),
                new Category("iPhone"),
                new Category("Lenovo"),
                new Category("LG"),
                new Category("Meizu"),
                new Category("Microsoft"),
                new Category("Nokia"),
                new Category("Panasonic"),
                new Category("Siemens"),
                new Category("Sony"),
                new Category("Xiaomi"),
                new Category("Other brands"),
                new Category("Landline telephones"),
                new Category("Accessories", Set.of(
                        new Category("Batteries"),
                        new Category("Headsets and headphones"),
                        new Category("Charging device"),
                        new Category("Cables and adapters"),
                        new Category("Modems and routers"),
                        new Category("Covers and films"),
                        new Category("Spare parts")
                ))
        ));

        Category forComputers = new Category("Computer products", Set.of(
                new Category("Acoustics"),
                new Category("Webcams"),
                new Category("Joysticks and steering wheels"),
                new Category("Keyboards and mice"),
                new Category("Monitors"),
                new Category("Portable hard drives"),
                new Category("Network hardware"),
                new Category("TV tuners"),
                new Category("Flash drives and memory cards"),
                new Category("Accessories"),
                new Category("Components", Set.of(
                        new Category("CD, DVD and Blu-ray drives"),
                        new Category("Power supplies"),
                        new Category("Video cards"),
                        new Category("Hard drives"),
                        new Category("Sound cards"),
                        new Category("Controllers"),
                        new Category("Housings"),
                        new Category("Motherboards"),
                        new Category("RAM"),
                        new Category("Processors"),
                        new Category("Cooling systems")
                ))
        ));

        Category photo = new Category("Photography equipment", Set.of(
                new Category("Compact Cameras"),
                new Category("SLR Cameras"),
                new Category("Film cameras"),
                new Category("Binoculars and telescopes"),
                new Category("Lenses"),
                new Category("Equipment and accessories")
        ));

        category.setSubCategories(Set.of(audioVideo, games, desktops, laptops, officeEquipment,
                tablets, phones, forComputers, photo));
        mongoTemplate.save(category);
    }
}
