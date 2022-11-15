/**
 * @author aeon_flux aeon_flux@eclipso.ch
 */
open module jrosmoveit.tests {
    requires jrosmoveit;
    requires org.junit.jupiter.api;
    requires jrostf2;
    requires jrosrviztools;

    exports pinorobotics.jrosmoveit.tests;
    exports pinorobotics.robotstate.tests;
}
