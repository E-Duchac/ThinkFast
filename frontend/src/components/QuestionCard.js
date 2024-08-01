import React from "react";

//Not sure if I want to extract this or not...

const QuestionPanel = (questionText) => {
    const Italic = ({children}) => <i>{children}</i>;
    const Bold = ({children}) => <b>{children}</b>;
    const Sup = ({children}) => <sup>{children}</sup>;
    const Sub = ({children}) => <sub>{children}</sub>;

    const needsFormatting = /<i>|<b>|<sup>|<sub>/.test(questionText);

    const formatText = (text) => {
        const parts = text.split(/(<i>.*?<\/i>|<b>.*?<\/b>|<sup>.*?<\/sup>|<sub>.*?<\/sub>)/g);
        return parts.map((part, index) => {
            if (part.startsWith('<i>')) {
                return <Italic key={index}>{part.slice(3, -4)}</Italic>;
            } else if (part.startsWith('<b>')) {
                return <Bold key={index}>{part.slice(3, -4)}</Bold>;
            } else if (part.startsWith('<sup>')) {
                return <Sup key={index}>{part.slice(5, -6)}</Sup>;
            } else if (part.startsWith('<sub>')) {
                return <Sub key={index}>{part.slice(5, -6)}</Sub>;
            }
            return <React.Fragment key={index}>{part}</React.Fragment>
        });
    };

    return (
        <div className="question-panel">
          <p>
            {needsFormatting ? 
                formatText(questionText) :
                questionText}
          </p>
        </div>
    );
}

export default QuestionPanel;