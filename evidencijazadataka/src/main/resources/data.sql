INSERT INTO sprint (id, bodovi, ime) VALUES (1, 100, 'sprintJedan');
INSERT INTO sprint (id, bodovi, ime) VALUES (2, 200, 'sprintDva');

INSERT INTO stanje (id, ime) VALUES (1, 'zavrsen');
INSERT INTO stanje (id, ime) VALUES (2, 'u-toku');
INSERT INTO stanje (id, ime) VALUES (3, 'nov');

INSERT INTO zadatak (id, bodovi, ime, zaduzeni, sprint_id, stanje_id) VALUES (1, 30, 'prvi', 'zaduzeni', 1, 2);
INSERT INTO zadatak (id, bodovi, ime, zaduzeni, sprint_id, stanje_id) VALUES (2, 30, 'drugi', 'nezaduzeni', 2, 3);
INSERT INTO zadatak (id, bodovi, ime, zaduzeni, sprint_id, stanje_id) VALUES (3, 30, 'treci', 'zaduzeni', 1, 1);

INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (1,'miroslav@maildrop.cc','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (2,'tamara@maildrop.cc','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','KORISNIK');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (3,'petar@maildrop.cc','petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','Petar','Jovic','KORISNIK');
