import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search',
  imports: [],
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent implements OnInit {

  constructor(private router: Router) {

  }
ngOnInit(): void {
  throw new Error('Method not implemented.');
}


doSearch(value: string) {
  console.log(`value=${value}`);
  this.router.navigateByUrl(`/search/${value}`);
}

}
