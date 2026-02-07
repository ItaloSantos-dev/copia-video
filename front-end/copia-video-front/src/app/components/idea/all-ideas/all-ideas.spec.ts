import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllIdeas } from './all-ideas';

describe('AllIdeas', () => {
  let component: AllIdeas;
  let fixture: ComponentFixture<AllIdeas>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AllIdeas]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AllIdeas);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
