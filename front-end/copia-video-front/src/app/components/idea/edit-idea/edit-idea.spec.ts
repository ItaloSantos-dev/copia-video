import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditIdea } from './edit-idea';

describe('EditIdea', () => {
  let component: EditIdea;
  let fixture: ComponentFixture<EditIdea>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditIdea]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditIdea);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
