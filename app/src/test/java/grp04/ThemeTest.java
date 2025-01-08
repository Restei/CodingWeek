package grp04;

import static org.junit.jupiter.api.Assertions.*;

import grp04.jeu.modele.Theme;
import org.junit.jupiter.api.Test;

public class ThemeTest {

    @Test void CreationThemeTest() {
        Theme theme1 = new Theme("theme1");
        Theme theme2 = new Theme("theme2");
        assertNotEquals(theme1.getId(), theme2.getId());
    }

    @Test void ajouterMotThemeTest() {
        Theme theme = new Theme("theme");
        theme.addMot("mot1");
        theme.addMot("mot1");
        theme.addMot("mot2");
        assertEquals(2, theme.getMots().size());
        theme.removeMot("mot1");
        assertEquals("mot2", theme.getMots().getFirst());
        assertTrue(theme.estDansMots("mot2"));
    }

}
