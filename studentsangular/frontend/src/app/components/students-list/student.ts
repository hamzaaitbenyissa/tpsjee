export interface Student {
  id: number;
  nom: String;
  prenom: String;
  email: String;
  datenaissance: String;
  enregle: boolean;
  genre: Genre;
}

export enum Genre {
  M,
  F,
}


