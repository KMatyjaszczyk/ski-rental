package pl.itkurnik.skirental.domain.equipment.util;

import pl.itkurnik.skirental.domain.equipment.Equipment;

import java.util.UUID;

public class EquipmentImageUuidGenerator {

    public static String generate(Equipment equipment, String fileExtension) {
        Integer categoryId = equipment.getEquipmentCategory().getId();
        Integer equipmentId = equipment.getId();
        String generatedUuid = UUID.randomUUID().toString();

        return String.format("cat%d_eq%d_%s.%s",
                categoryId, equipmentId, generatedUuid, fileExtension);
    }
}
