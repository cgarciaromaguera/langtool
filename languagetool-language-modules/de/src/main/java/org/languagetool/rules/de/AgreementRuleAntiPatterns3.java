/* LanguageTool, a natural language style checker
 * Copyright (C) 2022 Daniel Naber (http://www.danielnaber.de)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301
 * USA
 */
package org.languagetool.rules.de;

import org.languagetool.JLanguageTool;
import org.languagetool.rules.patterns.PatternToken;
import org.languagetool.rules.patterns.PatternTokenBuilder;

import java.util.Arrays;
import java.util.List;

import static org.languagetool.rules.patterns.PatternRuleBuilderHelper.*;

class AgreementRuleAntiPatterns3 {

  static final List<List<PatternToken>> ANTI_PATTERNS = Arrays.asList(
    Arrays.asList(
      // "Was in aller Welt soll das denn für ein Satz sein?"
      token("was"),
      token("in"),
      token("aller"),
      new PatternTokenBuilder().token("Welt").setSkip(4).build(),
      token("für"),
      token("ein"),
      new PatternTokenBuilder().posRegex("ADJ:.*(MAS|NEU).*").min(0).build(),
      posRegex("SUB:.*:(MAS|NEU).*")
    ),
    Arrays.asList(
      // "Was in aller Welt soll das denn für ein Satz sein?"
      token("was"),
      token("in"),
      token("aller"),
      new PatternTokenBuilder().token("Welt").setSkip(4).build(),
      token("für"),
      token("eine"),
      new PatternTokenBuilder().posRegex("ADJ:.*FEM.*").min(0).build(),
      posRegex("SUB:.*:FEM.*")
    ),
    Arrays.asList(  // "wird das schwere Konsequenzen haben"
      token("das"),
      token("schwere"),
      token("Konsequenzen")
    ),
    Arrays.asList(  // "der Chaos Computer Club"
      token("der"),
      token("Chaos"),
      token("Computer"),
      token("Club")
    ),
    Arrays.asList(  // "der Echo Show" (Amazon device)
      token("Echo"),
      tokenRegex("Show|Dot")
    ),
    Arrays.asList(  // "In einem App Store"
      tokenRegex("App|Play"),
      token("Store")
    ),
    Arrays.asList(
      token("Knecht"),
      token("Ruprecht")
    ),
    Arrays.asList(  // "in dem einen Jahr"
      token("dem"),
      token("einen"),
      pos("SUB:NOM:SIN:NEU")
    ),
    Arrays.asList(  // -> "Kaffee" - handled by spell checker
      tokenRegex("eine[sn]"),
      tokenRegex("Kaffes?")
    ),
    Arrays.asList(  // "Dies erlaubt Forschern, ..." aber auch "Dieses versuchten Mathematiker ..."
      pos("SENT_START"),
      posRegex("PRO:DEM:.+"),
      posRegex("VER:3:.+"),
      posRegex("SUB:(DAT|NOM):PLU.*")
    ),
    Arrays.asList(  // "Das verkündete Premierminister Miller"
      pos("SENT_START"),
      token("das"),
      token("verkündete"),
      posRegex("SUB:.*")
    ),
    Arrays.asList(  // "Das verkündete Premierminister Miller"
      token("wegen"),
      token("der"),
      token("vielen"),
      token("Arbeit")
    ),
    Arrays.asList(  // "in denen Energie steckt"
      new PatternTokenBuilder().posRegex("SENT_START|VER:AUX:[123].+").negate().build(),
      posRegex("PRP:.+"),
      new PatternTokenBuilder().posRegex("PRO:DEM:(DAT|AKK).+").tokenRegex("der|dies").matchInflectedForms().build(),
      posRegex("SUB:...:PLU.*")
    ),
    Arrays.asList(  // "ein für mich sehr peinlicher Termin"
      token("für"),
      token("mich"),
      pos("ADV:MOD"),
      posRegex("ADJ:.*"),
      posRegex("SUB:.*")
    ),
    Arrays.asList(  // "für den Mailänder Bischofssitz"
      posRegex("PRP:.+"),
      new PatternTokenBuilder().posRegex("PRO:DEM:(DAT|AKK).+").tokenRegex("der|dies").matchInflectedForms().build(),
      new PatternTokenBuilder().csTokenRegex("[A-ZÄÖÜ].+er").build(),
      new PatternTokenBuilder().posRegex("SUB.+").build()
    ),
    Arrays.asList(
      posRegex("PRP:.+"),
      posRegex("PRO:DEM:(DAT|AKK).+"),
      posRegex("PA2:(DAT|AKK).+"),
      posRegex("SUB:(DAT|AKK):.*")
    ),
    Arrays.asList( // Artikel 34 setzt dem bestimmte Formen gleich
      posRegex("VER:.*[123].*"),
      posRegex("PRO:DEM:DAT:SIN:NEU.*"),
      posRegex("PA2:AKK:PLU.+"),
      posRegex("SUB:AKK:PLU.+")
    ),
    Arrays.asList( // Er stellt dieses interessierten Domänen zur Verfügung
      posRegex("VER:.*[123].*"),
      posRegex("PRO:DEM:AKK:SIN:NEU.*"),
      posRegex("PA2:DAT:PLU.+"),
      posRegex("SUB:DAT:PLU.+")
    ),
    Arrays.asList(
      pos("ADJ:PRD:KOM"),
      csToken("als"),
      regex("d(er|ie|as)"),
      posRegex(".+:GEN:.+")
    ),
    Arrays.asList(  // "Wir bereinigen das nächsten Dienstag."
      posRegex("VER:.*|UNKNOWN"),
      token("das"),
      csRegex("(über)?nächste[ns]?|kommende[ns]?|(vor)?letzten"),
      csRegex("Januar|Februar|März|April|Mai|Juni|Juli|August|September|Oktober|November|Dezember|Montag|D(ien|onner)stag|Mittwoch|Freitag|S(ams|onn)tag|Sonnabend|Woche|Monat|Jahr|Morgens?|Abends|Übermorgen|Mittags?|Nachmittags?|Vormittags?|Spätabends?|Nachts?")
    ),
    Arrays.asList(  // "Wir releasen das Montag.", "Wir präsentierten das Januar."
      posRegex("VER:.*|UNKNOWN"),
      csToken("das"),
      csRegex("Januar|Februar|März|April|Mai|Ju[nl]i|August|September|Oktober|November|Dezember|Montags?|D(ien|onner)stags?|Mittwochs?|Freitags?|S(ams|onn)tags?|Sonnabends?|Morgens?|Abends|Übermorgen|Mittags?|Nachmittags?|Vormittags?|Spätabends?|Nachts?")
    ),
    Arrays.asList(  // "Kannst du das Mittags machen?"
      token("das"),
      tokenRegex("Januar|Februar|März|April|Mai|Ju[nl]i|August|September|Oktober|November|Dezember|Montags?|D(ien|onner)stags?|Mittwochs?|Freitags?|S(ams|onn)tags?|Sonnabends?|Morgens?|Abends|Übermorgen|Mittags?|Nachmittags?|Vormittags?|Spätabends?|Nachts?"),
      posRegex("VER:.*|UNKNOWN")
    ),
    Arrays.asList(  // "Kannst du das nächsten Monat machen?"
      token("das"),
      tokenRegex("(über)?nächste[ns]?|kommende[ns]?|(vor)?letzten|vorigen"),
      csRegex("Januar|Februar|März|April|Mai|Ju[nl]i|August|September|Oktober|November|Dezember|Montag|D(ien|onner)stag|Mittwoch|Freitag|S(ams|onn)tag|Sonnabend|Woche|Monat|Jahr|Morgens?|Abends|Übermorgen|Mittags?|Nachmittags?|Vormittags?|Spätabends?|Nachts?"),
      posRegex("VER:.*|UNKNOWN")
    ),
    Arrays.asList(
      token("das"),
      csRegex("Zufall|Sinn|Spa(ß|ss)|Freude"),
      token("?")
    ),
    Arrays.asList(
      csRegex("w[äa]r|ist|sei"),
      token("das"),
      csRegex("Zufall|Spa(ß|ss)"),
      csRegex("\\.|\\?|!|,|…")
    ),
    Arrays.asList(
      // Dann sei das Zufall gewesen
      csRegex("w[äa]r|ist|sei"),
      token("das"),
      csRegex("Zufall|Spa(ß|ss)"),
      csRegex("gewesen")
    ),
    Arrays.asList(
       // "War das Zufall, dass es ging?"
      token("das"),
      csRegex("Zufall|Sinn|Spa(ß|ss)"),
      csToken(",")
    ),
    Arrays.asList(
      token("in"),
      tokenRegex("d(ies)?em"),
      token("Fall"),
      tokenRegex("(?i:hat(te)?)"),
      token("das")
    ),
    Arrays.asList( // "So hatte das Vorteile|Auswirkungen|Konsequenzen..."
      posRegex("ADV:.+"),
      tokenRegex("(?i:hat(te)?)"),
      csToken("das")
    ),
    Arrays.asList(
      tokenRegex("von|bei"),
      csRegex("vielen|allen|etlichen"),
      posRegex("PA2:.*|ADJ:AKK:PLU:.*")  // "ein von vielen bewundertes Haus" / "Das weckte bei vielen ungute Erinnerungen."
    ),
    Arrays.asList(
      // "Der letzte Woche vom Rat der Justizminister gefasste Beschluss..."
      tokenRegex("de[mnr]|die|das"),
      csRegex("letzte[ns]?|vorige[ns]?"),
      csRegex("Woche|Monat|Jahr(zehnt|hundert)?"),
      posRegex("PRP:.*"),
      posRegex("SUB:.*"),
      posRegex("ART:.*"),
      posRegex("SUB:.*"),
      posRegex("PA2:.*")
    ),
    Arrays.asList(
      token("für"),
      csRegex("(viele|etliche|alle|[dm]ich|ihn|sie|uns|andere|jeden)"),
      posRegex("ADJ:NOM:.*")  // "Ein für viele wichtiges Anliegen."
    ),
    Arrays.asList(
      new PatternTokenBuilder().tokenRegex("flö(ß|ss)en|machen|jagen").matchInflectedForms().build(),
      csRegex("einem|jedem|keinem"),
      csToken("Angst")  // "Dinge, die/ Etwas, das einem Angst macht"
    ),
    Arrays.asList(
      tokenRegex("einem|jedem|keinem"),
      csToken("Angst"),  // "Was einem Angst macht"
      new PatternTokenBuilder().tokenRegex("machen|ein(flö(ß|ss)en|jagen)").matchInflectedForms().build()
    ),
    Arrays.asList(
      token("einem"),
      csToken("geschenkten"),
      csToken("Gaul")
    ),
    Arrays.asList( // "Wir wollen sein ein einzig Volk von Brüdern" -- Schiller
      csToken("ein"),
      csToken("einzig"),
      csToken("Volk"),
      csToken("von")
    ),
    Arrays.asList( // "Lieber den Spatz in der Hand"
      csToken("den"),
      csToken("Spatz"),
      csToken("in")
    ),
    Arrays.asList(
      token("kein"),
      csToken("schöner"),
      csToken("Land")  // https://de.wikipedia.org/wiki/Kein_sch%C3%B6ner_Land
    ),
    Arrays.asList(
      tokenRegex("die|der|das"),
      csRegex("Anfang|Mitte|Ende"),
      csRegex("Januar|Jänner|Februar|März|April|Mai|Ju[ln]i|August|September|Oktober|November|Dezember|[12][0-9]{3}")
    ),
    Arrays.asList( // Waren das schwierige Entscheidungen?
      csRegex("Ist|Sind|War|Waren|Macht|Wird|Werden"),
      csToken("das"),
      new PatternTokenBuilder().posRegex("ADJ:NOM.*").min(0).build(),
      posRegex("SUB:NOM.*"),
      posRegex("PKT|KON:NEB|PRP.+")// "Ist das Kunst?" / "Ist das Kunst oder Abfall?" / "Sind das Eier aus Bodenhaltung"
    ),
    Arrays.asList( // Soll das Demokratie sein?
      posRegex("SENT_START|PKT|KON:NEB"),
      regex("soll|sollen|sollte|wird|werden|würde|kann|können|könnte|muss|müssen|müsste"),
      csToken("das"),
      new PatternTokenBuilder().posRegex("ADJ:NOM.*").min(0).build(),
      posRegex("SUB:NOM.*"),
      csRegex("sein|werden")
    ),
    Arrays.asList( // Hat das Spaß gemacht?
      posRegex("SENT_START|PKT|KON:NEB"),
      regex("hat|hatte"),
      csToken("das"),
      new PatternTokenBuilder().posRegex("ADJ:NOM.*").min(0).build(),
      csRegex("Spa(ß|ss)|Freude|Sinn|Mehrwert"),
      csRegex("gemacht|ergeben|gestiftet")
    ),
    Arrays.asList( // Eine Lösung die Spaß macht
      regex("die|der|das"),
      new PatternTokenBuilder().posRegex("ADJ:NOM.*").min(0).build(),
      csRegex("Spa(ß|ss)|Freude|Sinn|Mehrwert"),
      new PatternTokenBuilder().tokenRegex("machen|schaffen|stiften|ergeben").matchInflectedForms().build()
    ),
    Arrays.asList( // Soll das Spaß machen?
      posRegex("SENT_START|PKT|KON:NEB"),
      regex("soll|sollte|wird|würde|kann|könnte"),
      csToken("das"),
      new PatternTokenBuilder().posRegex("ADJ:NOM.*").min(0).build(),
      csRegex("Spa(ß|ss)|Freude|Sinn|Mehrwert"),
      csRegex("machen|stiften|ergeben")
    ),
    Arrays.asList( // Die Präsent AG ("Theater AG" is found via DE_COMPOUNDS)
      csRegex("[A-ZÄÖÜ].+"),
      csRegex("AG|GmbH|SE")
    ),
    Arrays.asList( // Die Otto Christ AG
      csRegex("[A-ZÄÖÜ].+"),
      csRegex("[A-ZÄÖÜ].+"),
      csRegex("AG|GmbH|SE|KG")
    ),
    Arrays.asList( // Die Otto Christ AG
      posRegex("ART.*"),
      csRegex("[A-ZÄÖÜ].+"),
      csRegex("[A-ZÄÖÜ].+"),
      csRegex("AG|GmbH|SE|KG")
    ),
    Arrays.asList(// Die Ernst Klett Schulbuch AG
      csRegex("[A-ZÄÖÜ].+"),
      csRegex("[A-ZÄÖÜ].+"),
      csRegex("[A-ZÄÖÜ].+"),
      csRegex("AG|GmbH|SE|KG")
    ),
    Arrays.asList( // Die damalige Klett AG
      token("die"),
      new PatternTokenBuilder().posRegex("ADJ:NOM:SIN:FEM.*").csTokenRegex("[a-zäöü].+").min(0).build(),
      csRegex("[A-ZÄÖÜ].+"),
      csRegex("AG|GmbH|SE")
    ),
    Arrays.asList( // Die damalige Ernst Klett AG
      token("die"),
      new PatternTokenBuilder().posRegex("ADJ:NOM:SIN:FEM.*").csTokenRegex("[a-zäöü].+").min(0).build(),
      csRegex("[A-ZÄÖÜ].*"),
      csRegex("[A-ZÄÖÜ].*"),
      csRegex("AG|GmbH|SE")
    ),
    Arrays.asList( // Die damalige Ernst Klett Schulbuch AG
      token("die"),
      new PatternTokenBuilder().posRegex("ADJ:NOM:SIN:FEM.*").csTokenRegex("[a-zäöü].+").min(0).build(),
      csRegex("[A-ZÄÖÜ].*"),
      csRegex("[A-ZÄÖÜ].*"),
      csRegex("[A-ZÄÖÜ].*"),
      csRegex("AG|GmbH|SE")
    ),
    Arrays.asList(
      // like above, but with ":", as we don't interpret this as a sentence start (but it often is)
      csRegex("Meist(ens)?|Oft(mals)?|Häufig|Selten|Natürlich"),
      tokenRegex("sind|waren|ist"),
      token("das"),
      posRegex("SUB:.*") // Meistens sind das Frauen, die damit besser umgehen können.
    ),
    Arrays.asList( // Natürlich ist das Quatsch!
      tokenRegex("ist|war"),
      token("das"),
      token("Quatsch")
    ),
    Arrays.asList( // Eine Maßnahme die Vertrauen schafft
      tokenRegex("der|die"),
      token("Vertrauen"),
      new PatternTokenBuilder().matchInflectedForms().tokenRegex("schaffen").build()
    ),
    Arrays.asList(
      token("des"),
      token("Lied"),
      token("ich") // Wes Brot ich ess, des Lied ich sing
    ),
    Arrays.asList( // Es ist einige Grad kälter (see example on https://www.duden.de/rechtschreibung/Grad)
      token("einige"),
      token("Grad")
    ),
    Arrays.asList( // Ein dickes Danke an alle die ...
      token("ein"),
      posRegex("ADJ:.+"),
      token("Danke")
    ),
    Arrays.asList(
      pos(JLanguageTool.SENTENCE_START_TAGNAME),
      tokenRegex("D(a|ie)s"),
      posRegex("VER:[123]:.*"),
      posRegex("SUB:NOM:.*")// "Das erfordert Können und..." / "Dies bestätigte Polizeimeister Huber"
    ),
    Arrays.asList(
      // like above, but with ":", as we don't interpret this as a sentence start (but it often is)
      token(":"),
      tokenRegex("D(a|ie)s"),
      posRegex("VER:[123]:.*"),
      posRegex("SUB:NOM:.*")// "Das erfordert Können und..." / "Dies bestätigte Polizeimeister Huber"
    ),
    Arrays.asList(
      posRegex("ART:.+"), // "Das wenige Kilometer breite Tal"
      posRegex("ADJ:.+"),
      tokenRegex("(Kilo|Zenti|Milli)?meter|Jahre|Monate|Wochen|Tage|Stunden|Minuten|Sekunden")
    ),
    Arrays.asList(
      token("van"), // https://de.wikipedia.org/wiki/Alexander_Van_der_Bellen
      token("der")
    ),
    Arrays.asList(
      token("mehrere"), // "mehrere Verwundete" http://forum.languagetool.org/t/de-false-positives-and-false-false/1516
      pos("SUB:NOM:SIN:FEM:ADJ")
    ),
    Arrays.asList(
      token("allen"),
      tokenRegex("Besitz|Mut")
    ),
    Arrays.asList(
      tokenRegex("d(ie|e[nr])|[md]eine[nr]?|(eure|unsere)[nr]?|diese[nr]?"),
      token("Top"),
      tokenRegex("\\d+")
    ),
    Arrays.asList(
      tokenRegex("d(ie|e[nr])|[md]eine[nr]?|(eure|unsere)[nr]?|diese[nr]?"),
      posRegex("(ADJ|PA[12]).+"),
      token("Top"),
      tokenRegex("\\d+")
    ),
    Arrays.asList( //"Unter diesen rief das großen Unmut hervor."
      posRegex("VER:3:SIN:.*"),
      token("das"),
      posRegex("ADJ:AKK:.*"),
      posRegex("SUB:AKK:.*"),
      pos("ZUS"),
      pos(JLanguageTool.SENTENCE_END_TAGNAME)
    ),
    Arrays.asList( // "Bei mir löste das Panik aus."
      posRegex("VER:3:SIN:.+"),
      token("das"),
      posRegex("SUB:AKK:.+"),
      pos("ZUS"),
      pos(JLanguageTool.SENTENCE_END_TAGNAME)
    ),
    Arrays.asList(
      token("der"),
      token("viele"),
      tokenRegex("Schnee|Regen")
    ),
    Arrays.asList(
      tokenRegex("der|die"),
      tokenRegex("vielen?"),
      token("Aufmerksamkeit")
    ),
    Arrays.asList(
      tokenRegex("Au(ß|ss)enring"),
      token("Autobahn")
    ),
    Arrays.asList(
      tokenRegex("Senior|Junior"),
      tokenRegex("Leaders?"),
      tokenRegex("Days?")
    ),
    Arrays.asList(
      // ich habe meine Projektidee (die riesiges finanzielles Potenzial hat) an einen Unternehmenspräsidenten geschickt
      posRegex("SUB.*(FEM|PLU).*|EIG.*FEM.*|UNKNOWN"),
      token("("),
      token("die")
    ),
    Arrays.asList(
      // Wir sind immer offen für Mitarbeiter die Teil eines der traditionellsten Malerbetriebe auf dem Platz Zürich werden möchten.
      posRegex("PRP.*"),
      posRegex("SUB.*PLU.*"),
      token("die"),
      posRegex("SUB.*SIN.*")
    ),
    Arrays.asList(
      posRegex("SUB.*MAS.*|EIG.*MAS.*|UNKNOWN"),
      token("("),
      token("de[rm]")
    ),
    Arrays.asList(
      posRegex("SUB.*NEU.*|EIG.*NEU.*|UNKNOWN"),
      token("("),
      token("das")
    ),
    Arrays.asList(
      pos("KON:UNT"), // "dass das komplett verschiedene Dinge sind"
      tokenRegex("der|das|dies"),
      new PatternTokenBuilder().pos("ADJ:PRD:GRU").min(0).build(),
      posRegex("ADJ.*PLU.*SOL|PA2.*PLU.*SOL:VER"),
      posRegex("SUB.*PLU.*")
    ),
    Arrays.asList(
      pos("KON:UNT"), // "ob die wirklich zusätzliche Gebühren abdrücken"
      token("die"),
      new PatternTokenBuilder().pos("ADJ:PRD:GRU").min(0).build(),
      posRegex("ADJ.*(NOM|AKK):PLU.*SOL|PA2.*(NOM|AKK):PLU.*SOL:VER"),
      posRegex("SUB.*(NOM|AKK):PLU.*")
    ),
    Arrays.asList(
      tokenRegex("Ende|Mitte|Anfang"), // "Ende 1923"
      tokenRegex("1[0-9]{3}|20[0-9]{2}")
    ),
    Arrays.asList(
      tokenRegex("dann|so"),
      token("bedarf"),
      tokenRegex("das|dies")
    ),
    Arrays.asList(
      posRegex("ART.*|PRO:POS.*"),
      posRegex("ADJ.*|PA[12].*"),
      tokenRegex("Windows|iOS"),
      tokenRegex("\\d+")
    ),
    Arrays.asList(
      // Die letzte unter Windows 98 lauffähige Version ist 5.1.
      posRegex("ART.*|PRO:POS.*"),
      posRegex("ADJ.*|PA[12].*"),
      posRegex("ADJ.*|PA[12].*"),
      tokenRegex("Windows|iOS"),
      tokenRegex("\\d+")
    ),
    Arrays.asList(
      posRegex("ART.*|PRO:POS.*"),
      tokenRegex("Windows|iOS"),
      tokenRegex("\\d+")
    ),
    // wird empfohlen, dass Unternehmen die gefährliche Güter benötigen ...
    Arrays.asList(
      token("dass"),
      new PatternTokenBuilder().posRegex("ADJ.*|PA[12].*").min(0).build(),
      posRegex("SUB:.*PLU.*"),
      token("die"),
      posRegex("ADJ.*|PA[12].*"),
      posRegex("SUB:.*"),
      posRegex("VER:.*")
    ),
    Arrays.asList( // des Handelsblatt Research Institutes
      csToken("Handelsblatt"),
      csToken("Research"),
      csRegex("Institute?s?")
    ),
    Arrays.asList( // Ich arbeite bei der Shop Apotheke im Vertrieb
      csToken("Shop"),
      csToken("Apotheke")
    ),
    Arrays.asList( // In den Prime Standard
      csToken("Prime"),
      csToken("Standard")
    ),
    Arrays.asList( // Die Nord Stream 2 AG
      csToken("Nord"),
      csToken("Stream")
    ),
    Arrays.asList( // Ein Mobiles Einsatzkommando
      posRegex("ART.*|PRO:POS.*"),
      csToken("Mobiles"),
      csToken("Einsatzkommando")
    ),
    Arrays.asList( // Die Gen Z
      posRegex("ART.*|PRO:POS.*"),
      csToken("Gen"),
      tokenRegex("[XYZ]")
    ),
    Arrays.asList( // Das veranlasste Bürgermeister Adam
      tokenRegex("das|dies"),
      csToken("veranlasste"),
      posRegex("SUB.*")
    ),
    Arrays.asList( // In einem Eins gegen Eins
      tokenRegex("ein|einem"),
      token("Eins"),
      csToken("gegen"),
      token("Eins")
    ),
    Arrays.asList( // Dann musst du das Schritt für Schritt …
      tokenRegex("das|dies"),
      token("Schritt"),
      csToken("für"),
      token("Schritt")
    ),
    Arrays.asList( // Das hat etliche Zeit in Anspruch genommen
      token("etliche"),
      token("Zeit")
    ),
    Arrays.asList( // Ich habe auf vieles Lust
      token("auf"),
      token("vieles"),
      tokenRegex("Lust|Bock")
    ),
    Arrays.asList( // Ich habe für vieles Zeit
      token("für"),
      token("vieles"),
      token("Zeit")
    ),
    Arrays.asList(
      // …, kann das Infektionen möglicherweise verhindern
      posRegex("KON.*|PKT|SENT_START"),
      new PatternTokenBuilder().posRegex("ADV.*").min(0).build(),
      posRegex("VER:MOD:3:SIN.*"),
      csToken("das"),
      posRegex("SUB.*"),
      new PatternTokenBuilder().posRegex("ADV.*").min(0).max(2).build(),
      posRegex("VER:INF:.*")
    ),
    Arrays.asList(
      // die gegnerischen Shooting Guards
      posRegex("(ART|PRO:POS).*NOM:PLU"),
      posRegex("(ADJ|PA[12]).*NOM:PLU.*"),
      posRegex("SUB.*SIN.*"),
      new PatternTokenBuilder().posRegex("UNKNOWN").csTokenRegex("[A-ZÖÄÜ][A-ZÖÄÜa-zöäüß\\-]+").build()
    ),
    Arrays.asList(
      // die gegnerischen Shooting Guards
      posRegex("(ART|PRO:POS).*GEN:PLU"),
      posRegex("(ADJ|PA[12]).*GEN:PLU.*"),
      posRegex("SUB.*SIN.*"),
      new PatternTokenBuilder().posRegex("UNKNOWN").csTokenRegex("[A-ZÖÄÜ][A-ZÖÄÜa-zöäüß\\-]+").build()
    ),
    Arrays.asList(
      // die gegnerischen Shooting Guards
      posRegex("(ART|PRO:POS).*DAT:PLU"),
      posRegex("(ADJ|PA[12]).*DAT:PLU.*"),
      posRegex("SUB.*SIN.*"),
      new PatternTokenBuilder().posRegex("UNKNOWN").csTokenRegex("[A-ZÖÄÜ][A-ZÖÄÜa-zöäüß\\-]+").build()
    ),
    Arrays.asList(
      // die gegnerischen Shooting Guards
      posRegex("(ART|PRO:POS).*AKK:PLU"),
      posRegex("(ADJ|PA[12]).*AKK:PLU.*"),
      posRegex("SUB.*SIN.*"),
      new PatternTokenBuilder().posRegex("UNKNOWN").csTokenRegex("[A-ZÖÄÜ][A-ZÖÄÜa-zöäüß\\-]+").build()
    ),
    Arrays.asList(
      // den leidenschaftlichen Lobpreis der texanischen Gateway Church aus
      posRegex("(ART|PRO:POS).*DAT:SIN.*"),
      posRegex("(ADJ|PA[12]).*DAT:SIN.*"),
      posRegex("SUB.*SIN.*"),
      new PatternTokenBuilder().posRegex("UNKNOWN").csTokenRegex("[A-ZÖÄÜ][A-ZÖÄÜa-zöäüß\\-]+").build()
    ),
    Arrays.asList(
      // den leidenschaftlichen Lobpreis des texanischen Gateway Church aus
      posRegex("(ART|PRO:POS).*GEN:SIN.*"),
      posRegex("(ADJ|PA[12]).*GEN:SIN.*"),
      posRegex("SUB.*SIN.*"),
      new PatternTokenBuilder().posRegex("UNKNOWN").csTokenRegex("[A-ZÖÄÜ][A-ZÖÄÜa-zöäüß\\-]+").build()
    ),
    Arrays.asList(
      // den leidenschaftlichen Lobpreis des texanischen Gateway Church aus
      posRegex("(ART|PRO:POS).*NOM:SIN.*"),
      posRegex("(ADJ|PA[12]).*NOM:SIN.*"),
      posRegex("SUB.*SIN.*"),
      new PatternTokenBuilder().posRegex("UNKNOWN").csTokenRegex("[A-ZÖÄÜ][A-ZÖÄÜa-zöäüß\\-]+").build()
    ),
    Arrays.asList(
      // den leidenschaftlichen Lobpreis des texanischen Gateway Church aus
      posRegex("(ART|PRO:POS).*AKK:SIN.*"),
      posRegex("(ADJ|PA[12]).*AKK:SIN.*"),
      posRegex("SUB.*SIN.*"),
      new PatternTokenBuilder().posRegex("UNKNOWN").csTokenRegex("[A-ZÖÄÜ][A-ZÖÄÜa-zöäüß\\-]+").build()
    ),
    Arrays.asList(
      // Von der ersten Spielminute an machten die Münsteraner Druck und ...
      new PatternTokenBuilder().matchInflectedForms().tokenRegex("machen").build(),
      token("die"),
      posRegex("SUB.*PLU.*"),
      tokenRegex("Druck")
    ),
    Arrays.asList(
      // Im Tun zu sein verhindert Prokrastination.
      token("zu"),
      token("sein"),
      posRegex("VER:3:SIN.*")
    ),
    Arrays.asList(
      tokenRegex("Ende|Mitte|Anfang"), // "Der Ende der achtziger Jahre umgestaltete ..."
      new PatternTokenBuilder().posRegex("ART:DEF:GEN:.*").min(0).build(),
      new PatternTokenBuilder().posRegex("ADJ.*:(GEN|DAT):.*|ZAL").build(),
      tokenRegex("Woche|Monats|Jahr(es?|zehnts|hunderts|tausends)")
    ),
    Arrays.asList(
      tokenRegex("Ende|Mitte|Anfang"), // "Ende letzten Jahres" "Ende der 50er Jahre"
      new PatternTokenBuilder().posRegex("ART:DEF:GEN:.*").min(0).build(),
      new PatternTokenBuilder().matchInflectedForms().tokenRegex("dieser|(vor)?letzter|[0-9]+er").build(),
      tokenRegex("Woche|Monats|Jahr(es?|zehnts|hunderts|tausends)")
    ),
    Arrays.asList(
      token("das"),
      csToken("Boostern")
    ),
    Arrays.asList(
      // Das Zeit.de-CMS / Das Zeit.de CMS
      token("das"),
      new PatternTokenBuilder().posRegex("(ADJ|PA[12]).+").min(0).build(),
      csToken("Zeit"),
      csToken("."),
      tokenRegex("de.*")
    ),
    Arrays.asList(
      token("das"),
      csToken("verlangte"),
      tokenRegex("Ruhe|Zeit|Geduld")
    ),
    Arrays.asList(
      csToken("BMW"),
      token("ConnectedDrive")
    ),
    Arrays.asList(
      // https://www.jungewirtschaft.at/
      token("die"),
      csToken("Junge"),
      csToken("Wirtschaft")
    ),
    Arrays.asList(
      token("der"),
      csToken("Jungen"),
      csToken("Wirtschaft")
    ),
    Arrays.asList(
      // Das passiert, weil die Schiss haben.
      token("die"),
      csRegex("Schiss|Mut|Respekt"),
      tokenRegex("haben|h[äa]tten?|zeigt?en|zollt?en")
    ),
    Arrays.asList(
      // "Inwiefern soll denn das romantische Hoffnungen begründen?"
      new PatternTokenBuilder().pos("ADV:MOD+INR").setSkip(-1).build(),
      new PatternTokenBuilder().posRegex("VER.*:[123]:SIN:.*").setSkip(1).build(),
      posRegex("PRO:DEM:.*SIN.*"),
      new PatternTokenBuilder().posRegex("ADJ:.*PLU.*").min(0).build(),
      posRegex("SUB:.*PLU.*"),
      posRegex("VER.*INF:.*")
    ),
    Arrays.asList(
      // 1944 eroberte diese weite Teile von Südosteuropa.
      posRegex("VER.*"),
      tokenRegex("diese[sr]?"),
      token("weite"),
      token("Teile")
    )
  );

}