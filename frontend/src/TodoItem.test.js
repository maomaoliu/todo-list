import React from 'react';
import { render, fireEvent, screen } from '@testing-library/react';
import TodoItem from './TodoItem';

describe('<TodoItem>', () => {
  const item = {
    id: 1,
    content: 'First Item',
    createAt: '2020/02/02',
    isComplete: false,
  };

  const onItemUpdate = () => {};
  const onItemDelete = () => {};
  const labelText = 'isComplete';

  beforeEach(() => {
    item.isComplete = false;
  });

  test('should render the checkbox currectly', async () => {
    render(
      <TodoItem
        item={item}
        onItemUpdate={onItemUpdate}
        onItemDelete={onItemDelete}
      />,
    );
    expect(screen.queryByLabelText(labelText)).toBeInTheDocument();
  });
});
