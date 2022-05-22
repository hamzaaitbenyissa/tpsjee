import { Component, OnInit } from '@angular/core';
import { Genre, Student } from './student';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-students-list',
  templateUrl: './students-list.component.html',
  styleUrls: ['./students-list.component.css'],
})
export class StudentsListComponent implements OnInit {
  students: Student[] = [];
  genreF: Genre = Genre.F;
  genreM: Genre = Genre.M;
  pages :Number[] = [1,2,3,4,5,6,7,8,9,10,11,12,13];

  constructor() {}

  myForm = new FormGroup({
    keyword: new FormControl(''),
  });

  get f() {
    return this.myForm.controls;
  }



  submit() {
    console.log('submitted');
  }
  ngOnInit(): void {
    this.students.push(
      {
        id: 3,
        nom: 'Leonor Christiansen V',
        prenom: 'Cecilia',
        email: 'elise.bauch@gmail.com',
        datenaissance: '1994-10-10',
        genre: Genre.F,
        enregle: false,
      },
      {
        id: 4,
        nom: 'Mr. Jennifer Crooks',
        prenom: 'Chance',
        email: 'reyes.upton@hotmail.com',
        datenaissance: '1958-06-25',
        genre: Genre.F,
        enregle: false,
      }
    );

    console.table(this.students);
  }

  // handle delete  event
  onDelete() {
    confirm('Are you sure you want to delete');
  }

  // handle edit event
  onEdit() {
    confirm('Are you sure you want to edit it ');
  }

}
