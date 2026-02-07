import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateIdea } from './create-idea';

describe('CreateIdea', () => {
  let component: CreateIdea;
  let fixture: ComponentFixture<CreateIdea>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateIdea]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateIdea);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
