/**
 * @author aeon_flux aeon_flux@eclipso.ch
 */
open module jrosmoveit.tests {
    requires jrosclient;
    requires jrosmoveit;
    requires jros1rviztools;
    requires id.xfunction;
    requires org.junit.jupiter.api;
    requires jrostf2;
    requires jrosrviztools;
    requires jrosmessages;

    exports pinorobotics.jrosmoveit.tests;
}
