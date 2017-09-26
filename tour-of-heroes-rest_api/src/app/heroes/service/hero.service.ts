import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Hero } from '../model/hero';
import { HEROES } from '../model/mock-heroes';
import { ApiService } from './api-services.config';

@Injectable()
export class HeroService {

    private heroesUrl = 'api/heroes';  // URL to web api
    private headers = new Headers({'Content-Type': 'application/json'});

    constructor(private http: Http) { }

    getHeroes(): Promise<Hero[]> {
        return this.http.get(ApiService.API_URL_LISTAR_HEROES)
        .toPromise()
        .then(response => response.json() as Hero[])
        .catch(this.handleError);
    }

    getHero(id: number): Promise<Hero> {
        //const url = `${this.heroesUrl}/${id}`;
        const url = `${ApiService.API_URL_CONSULTAR_HEROES}/?id=${id}`;
        return this.http.get(url)
          .toPromise()
          .then(response => response.json() as Hero)
          .catch(this.handleError);
    }

    update(hero: Hero): Promise<Hero> {
        const url = `${ApiService.API_URL_ACTUALIZAR_HEROES}/?id=${hero.id}&name=${hero.name}`;
        return this.http
          .get(url)
          .toPromise()
          .then(() => hero)
          .catch(this.handleError);
    }

    create(name: string): Promise<Hero> {
        const url = `${ApiService.API_URL_CREAR_HEROES}/?name=${name}`;
        return this.http
          .get(url)
          .toPromise()
          .then(res => res.json() as Hero)
          .catch(this.handleError);
    }

    delete(id: number): Promise<void> {
        const url = `${ApiService.API_URL_BORRAR_HEROES}/?id=${id}`;
        return this.http.get(url)
          .toPromise()
          .then(() => null)
          .catch(this.handleError);
      }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }
}