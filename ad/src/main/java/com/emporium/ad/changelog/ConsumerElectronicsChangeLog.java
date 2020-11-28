package com.emporium.ad.changelog;

import com.emporium.ad.model.jpa.Category;
import com.kuliginstepan.mongration.annotation.Changelog;
import com.kuliginstepan.mongration.annotation.Changeset;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Set;

@Changelog
public class ConsumerElectronicsChangeLog {

    @Changeset(order = 1,id = "initElectronic",author = "afakherutdinov")
    public void electronicInit(MongoTemplate mongoTemplate){
        Category category = new Category("Consumer Electronics");

        Category audioVideo = new Category(ObjectId.get(),"Audio and video", Set.of(
                new Category(ObjectId.get(),"MP3 players"),
                new Category(ObjectId.get(),"Acoustics, speakers, subwoofers"),
                new Category(ObjectId.get(),"Video, DVD and Blu-ray players"),
                new Category(ObjectId.get(),"Camcorders"),
                new Category(ObjectId.get(),"Cables and adapters"),
                new Category(ObjectId.get(),"Microphones"),
                new Category(ObjectId.get(),"Music and films"),
                new Category(ObjectId.get(),"Music centers, radio tape recorders"),
                new Category(ObjectId.get(),"Headphones"),
                new Category(ObjectId.get(),"Amplifiers and Receivers"),
                new Category(ObjectId.get(),"Accessories"),
                new Category(ObjectId.get(),"TVs and Projectors", Set.of(
                        new Category(ObjectId.get(),"TV sets"),
                        new Category(ObjectId.get(),"Projectors"),
                        new Category(ObjectId.get(),"Other")
                ))
        ));

        Category games = new Category(ObjectId.get(),"Games, consoles and programs", Set.of(
                new Category(ObjectId.get(),"Games for consoles"),
                new Category(ObjectId.get(),"Gaming consoles"),
                new Category(ObjectId.get(),"Computer games"),
                new Category(ObjectId.get(),"Programs")
        ));

        Category desktops = new Category(ObjectId.get(),"Desktop computers");
        Category laptops = new Category(ObjectId.get(),"Laptops");
        Category officeEquipment = new Category(ObjectId.get(),"Office equipment and consumables", Set.of(
                new Category(ObjectId.get(),"Printers"),
                new Category(ObjectId.get(),"Telephony"),
                new Category(ObjectId.get(),"Paper shredders"),
                new Category(ObjectId.get(),"Chancery"),
                new Category(ObjectId.get(),"MFPs, Copiers and Scanners", Set.of(
                        new Category(ObjectId.get(),"IFI"),
                        new Category(ObjectId.get(),"Copiers"),
                        new Category(ObjectId.get(),"Scanners"),
                        new Category(ObjectId.get(),"Other")
                )),
                new Category(ObjectId.get(),"UPS, surge protectors", Set.of(
                        new Category(ObjectId.get(),"UPS"),
                        new Category(ObjectId.get(),"Surge Protectors"),
                        new Category(ObjectId.get(),"Network filters"),
                        new Category(ObjectId.get(),"Other")
                )),
                new Category(ObjectId.get(),"Expendable materials", Set.of(
                        new Category(ObjectId.get(),"Power supplies and batteries"),
                        new Category(ObjectId.get(),"Blanks"),
                        new Category(ObjectId.get(),"Paper"),
                        new Category(ObjectId.get(),"Cables and adapters"),
                        new Category(ObjectId.get(),"Cartridges")
                ))
        ));

        Category tablets = new Category(ObjectId.get(), "Tablets and e-books", Set.of(
                new Category(ObjectId.get(),"Tablets"),
                new Category(ObjectId.get(),"EBooks"),
                new Category(ObjectId.get(),"Accessories", Set.of(
                        new Category(ObjectId.get(),"Batteries"),
                        new Category(ObjectId.get(),"Headsets and headphones"),
                        new Category(ObjectId.get(),"Docking stations"),
                        new Category(ObjectId.get(),"Charging device"),
                        new Category(ObjectId.get(),"Cables and adapters"),
                        new Category(ObjectId.get(),"Modems and routers"),
                        new Category(ObjectId.get(),"Styluses"),
                        new Category(ObjectId.get(),"Covers and tapes"),
                        new Category(ObjectId.get(),"Other")
                ))
        ));

        Category phones = new Category(ObjectId.get(), "Phones", Set.of(
                new Category(ObjectId.get(),"Acer"),
                new Category(ObjectId.get(),"HTC"),
                new Category(ObjectId.get(),"LG"),
                new Category(ObjectId.get(),"Huawei"),
                new Category(ObjectId.get(),"iPhone"),
                new Category(ObjectId.get(),"Lenovo"),
                new Category(ObjectId.get(),"LG"),
                new Category(ObjectId.get(),"Meizu"),
                new Category(ObjectId.get(),"Microsoft"),
                new Category(ObjectId.get(),"Nokia"),
                new Category(ObjectId.get(),"Panasonic"),
                new Category(ObjectId.get(),"Siemens"),
                new Category(ObjectId.get(),"Sony"),
                new Category(ObjectId.get(),"Xiaomi"),
                new Category(ObjectId.get(),"Other brands"),
                new Category(ObjectId.get(),"Landline telephones"),
                new Category(ObjectId.get(),"Accessories", Set.of(
                        new Category(ObjectId.get(),"Batteries"),
                        new Category(ObjectId.get(),"Headsets and headphones"),
                        new Category(ObjectId.get(),"Charging device"),
                        new Category(ObjectId.get(),"Cables and adapters"),
                        new Category(ObjectId.get(),"Modems and routers"),
                        new Category(ObjectId.get(),"Covers and films"),
                        new Category(ObjectId.get(),"Spare parts")
                ))
        ));

        Category forComputers = new Category(ObjectId.get(), "Computer products", Set.of(
                new Category(ObjectId.get(),"Acoustics"),
                new Category(ObjectId.get(),"Webcams"),
                new Category(ObjectId.get(),"Joysticks and steering wheels"),
                new Category(ObjectId.get(),"Keyboards and mice"),
                new Category(ObjectId.get(),"Monitors"),
                new Category(ObjectId.get(),"Portable hard drives"),
                new Category(ObjectId.get(),"Network hardware"),
                new Category(ObjectId.get(),"TV tuners"),
                new Category(ObjectId.get(),"Flash drives and memory cards"),
                new Category(ObjectId.get(),"Accessories"),
                new Category(ObjectId.get(),"Components", Set.of(
                        new Category(ObjectId.get(),"CD, DVD and Blu-ray drives"),
                        new Category(ObjectId.get(),"Power supplies"),
                        new Category(ObjectId.get(),"Video cards"),
                        new Category(ObjectId.get(),"Hard drives"),
                        new Category(ObjectId.get(),"Sound cards"),
                        new Category(ObjectId.get(),"Controllers"),
                        new Category(ObjectId.get(),"Housings"),
                        new Category(ObjectId.get(),"Motherboards"),
                        new Category(ObjectId.get(),"RAM"),
                        new Category(ObjectId.get(),"Processors"),
                        new Category(ObjectId.get(),"Cooling systems")
                ))
        ));

        Category photo = new Category(ObjectId.get(), "Photography equipment", Set.of(
                new Category(ObjectId.get(),"Compact Cameras"),
                new Category(ObjectId.get(),"SLR Cameras"),
                new Category(ObjectId.get(),"Film cameras"),
                new Category(ObjectId.get(),"Binoculars and telescopes"),
                new Category(ObjectId.get(),"Lenses"),
                new Category(ObjectId.get(),"Equipment and accessories")
        ));

        category.setSubCategories(Set.of(audioVideo,games,desktops,laptops, officeEquipment,
                tablets,phones,forComputers,photo));
        mongoTemplate.save(category);
    }
}
