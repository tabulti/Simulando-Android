package com.simulando.joaopaulodribeiro.simulando.utils;
import com.simulando.joaopaulodribeiro.simulando.R;

/**
 * Created by joao.paulo.d.ribeiro on 27/09/2017.
 */

public enum DisciplinesEnum {
    HISTORIA("1", "História", 0),
    PORTUGUES("2", "Português", 0),
    MATEMATICA("3", "Matemática", 0),
    QUIMICA("4", "Química", 0),
    GEOGRAFIA("5", "Geografia", 0),
    BIOLOGIA("6", "Biologia", R.drawable.bg_biologia),
    INGLES("7", "Inglês", 0),
    ESPANHOL("8", "Espanhol", 0),
    FISICA("9", "Física", 0),
    FILOSOFIA("10", "Filosofia", R.drawable.bg_filosofia),
    LITERATURA("11", "Literatura", 0),
    SOCIOLOGIA("12", "Sociologia", 0),
    ARTES("13", "Artes", 0);

    String id;
    String disciplineName;
    int backgroundImageId;

    DisciplinesEnum(String id, String disciplineName, int backgroundImageId) {
        this.id = id;
        this.disciplineName = disciplineName;
        this.backgroundImageId = backgroundImageId;
    }

    public String getId() {
        return this.id;
    }

    public String getDisciplineName() {
        return this.disciplineName;
    }

    public int getBackgroundImageId() {
        return backgroundImageId;
    }

    public static DisciplinesEnum getDisciplineById(String id) {
        if (!id.isEmpty()) {
            for (DisciplinesEnum discipline : DisciplinesEnum.values()) {
                if (id.equals(discipline.getId())) {
                    return discipline;
                }
            }
        }
        return null;
    }
}
