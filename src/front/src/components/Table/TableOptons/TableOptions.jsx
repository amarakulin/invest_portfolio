import { useRef } from 'react';
import { useDropdown } from '../../../hooks/useDropdown'
import OptionsDropdownMenu from './OptionsDropdownMenu/OptionsDropdownMenu'
import { Dots, DotsWrapper } from './TableOptionsStyles';

const TableOptions = (props) => {
	const optionsButtonRef = useRef();
	const [isOpen, toggleIsOpen] = useDropdown(optionsButtonRef);

	return (
		<DotsWrapper ref={optionsButtonRef} onClick={() => toggleIsOpen(true)}>
			<Dots />
			<OptionsDropdownMenu
				setEditMode={props.setEditMode}
				isOpen={isOpen}
				toggleIsOpen={toggleIsOpen}
				ticker={props.ticker}
			/>
		</DotsWrapper>
	)
}

export default TableOptions;